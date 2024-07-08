package com.example.projectoneecommerce

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.projectoneecommerce.cart.CartItem
import com.example.projectoneecommerce.data.SmartPhones

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DatabaseConstants.DATABASE_NAME,
    null,
    DatabaseConstants.DATABASE_VERSION
) {
    override fun onCreate(database: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE ${DatabaseConstants.TABLE_NAME}(
                ${DatabaseConstants.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${DatabaseConstants.COLUMN_PRODUCT_ID} INTEGER NOT NULL,
                ${DatabaseConstants.COLUMN_NAME} TEXT NOT NULL,
                ${DatabaseConstants.COLUMN_DESCRIPTION} TEXT NOT NULL,
                ${DatabaseConstants.COLUMN_PRICE} REAL NOT NULL,
                ${DatabaseConstants.COLUMN_QUANTITY} INTEGER NOT NULL,
                ${DatabaseConstants.COLUMN_IMAGE_RESOURCE} INTEGER NOT NULL
            )
        """.trimIndent()
        database?.execSQL(createTableQuery)
    }

    fun insertData(cartItem: CartItem): Long {
        val values = ContentValues().apply {
            put(DatabaseConstants.COLUMN_PRODUCT_ID, cartItem.item.id)
            put(DatabaseConstants.COLUMN_NAME, cartItem.item.name)
            put(DatabaseConstants.COLUMN_DESCRIPTION, cartItem.item.description)
            put(DatabaseConstants.COLUMN_PRICE, cartItem.item.price)
            put(DatabaseConstants.COLUMN_QUANTITY, cartItem.quantity)
            put(DatabaseConstants.COLUMN_IMAGE_RESOURCE, cartItem.item.img)
        }
        println("Here are the values__________________________________")
        println(values)
        return writableDatabase.insert(DatabaseConstants.TABLE_NAME, null, values)
    }

    fun readData(): List<CartItem> {
        val cartItemList = mutableListOf<CartItem>()

        val cursor = readableDatabase.query(
            DatabaseConstants.TABLE_NAME,
            null, null, null, null, null, null
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(DatabaseConstants.COLUMN_ID))
                val productId = getLong(getColumnIndexOrThrow(DatabaseConstants.COLUMN_PRODUCT_ID))
                val name = getString(getColumnIndexOrThrow(DatabaseConstants.COLUMN_NAME))
                val description = getString(getColumnIndexOrThrow(DatabaseConstants.COLUMN_DESCRIPTION))
                val price = getDouble(getColumnIndexOrThrow(DatabaseConstants.COLUMN_PRICE))
                val quantity = getInt(getColumnIndexOrThrow(DatabaseConstants.COLUMN_QUANTITY))
                val imgResource = getInt(getColumnIndexOrThrow(DatabaseConstants.COLUMN_IMAGE_RESOURCE))

                val item = SmartPhones(
                    id = productId,
                    name = name,
                    description = description,
                    price = price,
                    img = imgResource,
                    phoneType = "ios",  // Add default or retrieved phoneType
                    rating = 2.5f  // Add default or retrieved rating
                )

                cartItemList.add(CartItem(id = id, quantity = quantity, item = item, itemId = productId))
            }
            close()
        }

        return cartItemList
    }

    fun deleteProduct(id: Long): Int {
        val selection = "${DatabaseConstants.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        return writableDatabase.delete(DatabaseConstants.TABLE_NAME, selection, selectionArgs)
    }

    fun updateProduct(cartItem: CartItem): Int {
        val values = ContentValues().apply {
            put(DatabaseConstants.COLUMN_PRODUCT_ID, cartItem.itemId)
            put(DatabaseConstants.COLUMN_NAME, cartItem.item.name)
            put(DatabaseConstants.COLUMN_DESCRIPTION, cartItem.item.description)
            put(DatabaseConstants.COLUMN_PRICE, cartItem.item.price)
            put(DatabaseConstants.COLUMN_QUANTITY, cartItem.quantity)
            put(DatabaseConstants.COLUMN_IMAGE_RESOURCE, cartItem.item.img)
        }

        val selection = "${DatabaseConstants.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(cartItem.id.toString())

        return writableDatabase.update(DatabaseConstants.TABLE_NAME, values, selection, selectionArgs)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < newVersion) {
            db?.execSQL("DROP TABLE IF EXISTS ${DatabaseConstants.TABLE_NAME}")
            onCreate(db)
        }
    }

    fun decrementProductQuantity(id: Long): Int {
        return changeProductQuantity(id, decrement = true)
    }

    fun incrementProductQuantity(id: Long): Int {
        return changeProductQuantity(id, decrement = false)
    }

    private fun changeProductQuantity(id: Long, decrement: Boolean): Int {
        val db = writableDatabase
        val cursor = db.query(
            DatabaseConstants.TABLE_NAME,
            arrayOf(DatabaseConstants.COLUMN_QUANTITY),
            "${DatabaseConstants.COLUMN_ID} = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        var currentQuantity = 0
        if (cursor.moveToFirst()) {
            currentQuantity = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_QUANTITY))
        }
        cursor.close()

        if (decrement && currentQuantity > 1) {
            currentQuantity--
        } else if (!decrement) {
            currentQuantity++
        } else {
            return 0  // No change was made
        }

        val values = ContentValues().apply {
            put(DatabaseConstants.COLUMN_QUANTITY, currentQuantity)
        }

        return db.update(
            DatabaseConstants.TABLE_NAME,
            values,
            "${DatabaseConstants.COLUMN_ID} = ?",
            arrayOf(id.toString())
        )
    }
}
package ec.com.empresa.apputn004sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Productos {

    private SqliteAdminHelper admin;

    public Productos(Context context, String nombreBdd, int version)
    {
        admin = new SqliteAdminHelper(context, nombreBdd, null, version);
    }

    public Producto Create( int id, String nombre, double precio_unitario, double existencia )
    {
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues data = new ContentValues();

        data.put("id", id);
        data.put("nombre", nombre);
        data.put("precio_unitario", precio_unitario);
        data.put("existencia", existencia);

        long qty = db.insert("productos", "id,nombre,precio_unitario,existencia", data );
        if(qty == 1){
            Producto p = new Producto();
            p.Id = id;
            p.Nombre = nombre;
            p.Perecio_Unitario = precio_unitario;
            p.Existencia = existencia;
            return  p;
        }
        else
            return  null;
    }

    public Producto[] Read_All()
    {
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT id, nombre, precio_unitario, existencia FROM productos ORDER BY nombre", null);
        if( data.getCount() > 0)
        {
            Producto[] productos = new Producto[data.getCount()];
            Producto prod ;
            int i=0;
            while( data.moveToNext())
            {
                prod = new Producto();
                prod.Id = data.getInt(0);
                prod.Nombre = data.getString(1);
                prod.Perecio_Unitario = data.getDouble(2);
                prod.Existencia = data.getDouble(3);
                productos[i++]=prod;
            }
            return productos;
        }
        else
            return null;
    }

    public Producto Read_ById( int id)
    {
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT id, nombre, precio_unitario, existencia FROM productos WHERE id = " + id + " ORDER BY nombre", null);
        if( data.getCount() > 0)
        {
            Producto prod ;
            data.moveToNext();

            prod = new Producto();
            prod.Id = data.getInt(0);
            prod.Nombre = data.getString(1);
            prod.Perecio_Unitario = data.getDouble(2);
            prod.Existencia = data.getDouble(3);

            return prod;
        }
        else
            return null;
    }

    public int Update(int id, String nombre, double precio_unitario, double existencia)
    {
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues data = new ContentValues();

        data.put("id", id);
        data.put("nombre", nombre);
        data.put("precio_unitario", precio_unitario);
        data.put("existencia", existencia);

        int qty = db.update("productos", data, "id = " + id, null );
        return qty;
    }

    public int Delete(int id)
    {
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues data = new ContentValues();

        return db.delete("productos", "id = " + id, null );
    }
}

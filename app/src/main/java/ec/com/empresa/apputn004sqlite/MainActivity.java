package ec.com.empresa.apputn004sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtId, txtNombre, txtPrecioUnitario, txtExistencia;
    Productos productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId = findViewById(R.id.txtId);
        txtNombre = findViewById(R.id.txtNombre);
        txtPrecioUnitario = findViewById(R.id.txtPrecioUnitario);
        txtExistencia = findViewById(R.id.txtExistencia);

        productos = new Productos( this, "tienda.db", 1);
    }

    public void cmdInsert_onClick(View v)
    {
        Producto p = productos.Create(
                Integer.parseInt( txtId.getText().toString() ),
                txtNombre.getText().toString(),
                Double.parseDouble( txtPrecioUnitario.getText().toString() ),
                Double.parseDouble( txtExistencia.getText().toString() )
        );

        if ( p != null)
            Toast.makeText(this, "DATOS INSERTADOS !!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "ERROR AL INSERTAR DATOS !!", Toast.LENGTH_SHORT).show();
    }

    public void cmdRead_onClick(View v)
    {
        Producto p = productos.Read_ById( Integer.parseInt( txtId.getText().toString() ));
        if(p != null)
        {
            txtId.setText( "" + p.Id );
            txtNombre.setText( p.Nombre);
            txtPrecioUnitario.setText( ""+  p.Perecio_Unitario);
            txtExistencia.setText( "" + p.Existencia );
            Toast.makeText(this, "Datos leìdos correctamente", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "PRODUCTO NO ENCONTRADO !!", Toast.LENGTH_SHORT).show();
    }

    public  void cmdUpdate_onClick(View v)
    {
        if (productos.Update(
                Integer.parseInt( txtId.getText().toString() ),
                txtNombre.getText().toString(),
                Double.parseDouble( txtPrecioUnitario.getText().toString() ),
                Double.parseDouble( txtExistencia.getText().toString() )
          ) >0 )
        {
            Toast.makeText(this, "DATOS ACTUAKLIZADOS CORRECTAENTE !!", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "ERROR AL ACTUALIZAR PRODUCTO !!", Toast.LENGTH_SHORT).show();
    }

    public void cmdDelete_onClick(View v)
    {
        if ( productos.Delete( Integer.parseInt( txtId.getText().toString()  ) ) >0 )
        {
            txtId.setText("");
            txtPrecioUnitario.setText("");
            txtNombre.setText("");
            txtExistencia.setText("");
            Toast.makeText(this, "DATOS BORRADS EXITOSAMENTE !!", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "PRODUCTO NO EXISTE !!", Toast.LENGTH_SHORT).show();
    }
}
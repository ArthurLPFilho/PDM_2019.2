package filho.arthur.filasagencias.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import filho.arthur.filasagencias.FormularioHelper;
import filho.arthur.filasagencias.modelo.Agencia;

public class AgenciaDAO extends SQLiteOpenHelper {
    public AgenciaDAO(@Nullable Context context, int version) {

        super(context, "Agenda", null, 1);
    }
    private FormularioHelper helper;

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Agencias (id INTEGER PRIMARY KEY, numero TEXT NOT NULL, nome TEXT NOT NULL, endereco TEXT NOT NULL, horario TEXT, nota REAL, qtde REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Agencias";
        db.execSQL(sql);
        onCreate(db);
    }
    /*@NonNull
    private ContentValues pegaDadosDaAgencia(Agencia agencia) {
        ContentValues dados = new ContentValues();
        dados.put("numero", agencia.getNumero());
        dados.put("nome", agencia.getNome());
        dados.put("endereco", agencia.getEndereco());
        dados.put("horario", agencia.getHorario());
        dados.put("nota", agencia.getNota() + agencia.getNota());
        dados.put("qtde", agencia.getQtde() + 1);
        return dados;
    }*/

    public List<Agencia> buscaAgencias() {
        String sql = "SELECT * FROM Agencias";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Agencia> agencias = new ArrayList<Agencia>();
        while(c.moveToNext()){
            Agencia agencia = new Agencia();
            agencia.setId(c.getLong( c.getColumnIndex("id" )));
            agencia.setNumero(c.getString(c.getColumnIndex("numero")));
            agencia.setNome(c.getString(c.getColumnIndex("nome")));
            agencia.setEndereco(c.getString(c.getColumnIndex("endereco")));
            agencia.setHorario(c.getString(c.getColumnIndex("horario")));
            agencia.setNota(c.getDouble(c.getColumnIndex("nota")));
            agencia.setQtde(c.getDouble(c.getColumnIndex( "qtde" )));
            agencias.add(agencia);
        }
        c.close();
        return agencias;
    }
    public void insere(Agencia agencia) {
        SQLiteDatabase db = getWritableDatabase();
        //ContentValues dados = pegaDadosDaAgencia(agencia);
        ContentValues dados = new ContentValues();
        dados.put("numero", agencia.getNumero());
        dados.put("nome", agencia.getNome());
        dados.put("endereco", agencia.getEndereco());
        dados.put("horario", agencia.getHorario());
        dados.put("nota", agencia.getNota());
        dados.put("qtde", agencia.getQtde() + 1);
        db.insert("Agencias", null, dados);
    }
    public void altera(Agencia agencia) {
        //helper = new FormularioHelper(this);
        SQLiteDatabase db = getWritableDatabase();
        //ContentValues dados = pegaDadosDaAgencia( agencia );
        ContentValues dados = new ContentValues();
        dados.put("numero", agencia.getNumero());
        dados.put("nome", agencia.getNome());
        dados.put("endereco", agencia.getEndereco());
        dados.put("horario", agencia.getHorario());
        dados.put("nota", agencia.getNota());
        //dados.put("nota", agencia.getNota() + agencia.getNota());
        dados.put("qtde", agencia.getQtde() + 1);
        String params = (agencia.getId().toString());
        db.update( "Agencias", dados, "id = ?", new String[]{params} );
    }
}
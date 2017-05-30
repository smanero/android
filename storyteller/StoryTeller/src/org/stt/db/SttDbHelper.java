package org.stt.db;

import java.util.Iterator;

import org.stt.service.SttAssetsService;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SttDbHelper extends SQLiteOpenHelper {
	private Context context;
	
	public SttDbHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.v(this.getClass().getSimpleName(), "onCreate()");
		
		SttAssetsService sau = new SttAssetsService(this.context);
		this.onCreate(db, sau);
		sau.close();
	}
	/**
	 * Para lanzar scripts de creación
	 * @param db
	 * @param sau
	 */
	private void onCreate(SQLiteDatabase db, SttAssetsService sau){
		this.executeAssetScrtipt(db, SttAssetsService.FILE_CREATE, sau);

		//comprobemos las tablas creadas, si noes deja...
		this.verboseSchemaDB(db);

		//Insertar la primera historia, si nos deja....
		this.executeAssetScrtipt(db, SttAssetsService.FILE_HISTORY_00000001, sau);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SttDbHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");

		SttAssetsService sau = new SttAssetsService(this.context);
        //db.execSQL("DROP TABLE IF EXISTS notes");
//        onCreate(db);
        //borrar esquemas e indices
		this.executeAssetScrtipt(db, SttAssetsService.FILE_DROP, sau);

		this.onCreate(db, sau);//lanzar scripts de creación
		
		sau.close();//hemos terminado de manipular asserts
	}

	/**
	 * Ejecuta un script ubicado en la dirección relativa a assetPath
	 * @param db
	 * @param assetPath
	 */
	public void executeAssetScrtipt(SQLiteDatabase db, String assetPath, SttAssetsService sau){
		Log.v(this.getClass().getSimpleName(), "executeAssetScrtipt() inicio "+ assetPath);
		
		final Iterator<String> it = sau.getLineToLine(assetPath);
		String scriptStr = null;
		while(it.hasNext()){
			scriptStr = it.next().trim();
			if(scriptStr.length() > 0){//ignoramos líneas en blanco
				Log.v(this.getClass().getSimpleName(), scriptStr);
				db.execSQL(scriptStr);
			}
		}
		Log.v(this.getClass().getSimpleName(), "executeAssetScrtipt() fin ");
	}
	

    /**
     * Muestra las tablas creadas en el esquema para poder comprobar mejor que todo está bien.
     */
    public void verboseSchemaDB(SQLiteDatabase db){
    	Log.v(this.getClass().getSimpleName(), "verboseSchemaDB informando nombres de tablas");
    	
    	//Buscamos todas las tablas existentes y las mostramos
		final Cursor cursor;
		cursor = db.query("sqlite_master", new String[]{"name"}, "type=?", new String[]{"table"}, null, null, "name");

		String name = null;
		if (cursor != null && cursor.moveToFirst()) {
			do{
				name = cursor.getString(cursor.getColumnIndexOrThrow("name"));

				Log.v(this.getClass().getSimpleName(), name);
			}while(cursor.moveToNext());
        }
    	Log.v(this.getClass().getSimpleName(), "verboseSchemaDB finalizada lista de tablas");
    }
}

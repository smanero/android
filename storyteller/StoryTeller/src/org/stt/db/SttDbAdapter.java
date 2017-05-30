/**
 * 
 */
package org.stt.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.stt.db.table.ItemsTable;
import org.stt.service.SttAssetsService;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Gaizka
 * 
 */
public class SttDbAdapter {

   private static final String DATABASE_NAME = "storyTellerDB";
   private static final int DATABASE_VERSION = 1;

   private final Context context;// para gestionar e

   private SttDbHelper dbHelper;
   private SQLiteDatabase dbW = null; // writeable
   private boolean opened = false;

   private SQLiteDatabase getWritableDb() {
      open();
      if (dbW == null) {
         Log.v(this.getClass().getSimpleName(), "getWritableDb invoca a getWritableDatabase");

         dbW = dbHelper.getWritableDatabase();
      }
      return dbW;
   }

   /**
    * Constructor
    * 
    * @param context
    *           Takes the context to allow the database to be opened/created
    */
   public SttDbAdapter(Context context) {
      super();
      this.context = context;
   }

   /**
    * Open the notes database. If it cannot be opened, try to create a new instance of the database. If it cannot be
    * created, throw an exception to signal the failure
    * 
    * @return this (self reference, allowing this to be chained in an initialization call)
    * @throws SQLException
    *            if the database could be neither opened or created
    */
   public SQLiteOpenHelper open() throws SQLException {
      if (!opened) {
         this.opened = true;
         Log.v(this.getClass().getSimpleName(), "open");

         dbHelper = new SttDbHelper(this.context, SttDbAdapter.DATABASE_NAME, null, SttDbAdapter.DATABASE_VERSION);
      }
      return dbHelper;
   }

   /**
    * Close the DB
    */
   public void close() {
      if (this.opened) {
         Log.v(this.getClass().getSimpleName(), "close");
         dbHelper.close();
         this.opened = false;
      }
   }

   /**
    * Ejecuta un script ubicado en Assets
    * 
    * @param assetPath
    */
   public void executeAssetScript(String assetPath, SttAssetsService sau) {
      Log.v(this.getClass().getSimpleName(), "executeAssetScript(assetPath): " + assetPath);

      this.dbHelper.executeAssetScrtipt(this.getWritableDb(), assetPath, sau);
   }

   public HashMap<Object, LoadedBean> getAllRecords(ItemsTable table) {
      Log.v(this.getClass().getSimpleName(), "getAllRecords(table): " + table.getClass().getName());

      final Cursor cursor;
      cursor = this.getWritableDb().query(table.getTableName(), table.getColumns(), table.selectionFull(), null, null,
               null, null);

      // cargamos los datos en crudo
      final int szmp = cursor != null ? cursor.getCount() : 2;// para ahorrar recursos
      final HashMap<Object, LoadedBean> map = new HashMap<Object, LoadedBean>(szmp);
      LoadedBean ld = null;
      if (cursor != null && cursor.moveToFirst()) {
         do {
            ld = table.getLoadedBean(cursor);
            map.put(ld.key, ld);
         } while (cursor.moveToNext());
      }
      return map;
   }

   /**
    * El método tiene utilidad para tablas de relación como acciones_opcion o para listas ordenadas como textos. Lo que
    * importa no es la clave de los registros sino el orden en que llegan
    * 
    * @param table
    * @param args
    *           se espera como argumento el elemento a relacionar
    * @return lista de LoadedBean ordenada según se retorna de la consulta (el campo orden presumiblemente)
    */
   public List<LoadedBean> getRelatedRecords(ItemsTable table, Object args) {
      Log.v(this.getClass().getSimpleName(), "getRelatedRecords(table, args) : " + table.getClass().getName());
      if (args == null) {
         throw new UnsupportedOperationException("Falta argumento de consulta");
      }

      // Consultamos a la ddbb los registros relacionados
      final Cursor cursor;
      cursor = this.getWritableDb().query(table.getTableName(), table.getColumns(), table.selectionRelated(args),
               table.selectionRelatedArgs(args), null, null, table.getOrderByRelated());

      // cargamos los datos en crudo
      final int szmp = cursor != null ? cursor.getCount() : 2; // para ajustar consumo de recursos
      final List<LoadedBean> list = new ArrayList<LoadedBean>(szmp + 1);
      LoadedBean ld = null;
      if (cursor != null && cursor.moveToFirst()) {
         do {
            ld = table.getLoadedBean(cursor);
            list.add(ld);// lo que importa no es la clave (que es para todos igual) sino el orden en que llegan!!
         } while (cursor.moveToNext());
      }

      return list;
   }
}

package org.stt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.stt.db.SttDbAdapter;
import org.stt.db.table.ItemsTable;
import org.stt.model.history.Game;
import org.stt.model.history.History;
import org.stt.model.history.Option;
import org.stt.model.history.Page;
import org.stt.service.HistoryLoaderService;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * @author BIHEALGA
 * 
 */
public class StoryTellerMAC extends Activity {

//   public static Context _ctx;
   public static Activity _this;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      StoryTellerMAC._this = this;
      setContentView(R.layout.activity_main);

      History his;
      // his = this.makeASimpleHistory();
      his = readAFixedHistory(Integer.valueOf(1));

      Game.init(his, null, null);

      // lista de opciones
      final ListView optionsListView = (ListView) findViewById(R.id.listOptions);
      this.checkOptionAdapter = new OptionAdapter(this, R.layout.check_option_list_item);
      this.checkOptionAdapter.setOptionListener(new OptionOnClickListener());
      optionsListView.setAdapter(checkOptionAdapter);

      // zona de texto
      this.historyText = (TextView) findViewById(R.id.historyText);

      this.refrescar();
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.activity_main, menu);
      return true;
   }

   private TextView historyText;
   private OptionAdapter checkOptionAdapter;

   public History readAFixedHistory(Integer historyId) {
      ItemsTable.historiaId = historyId; // para que casi todos los accesos a
      // tablas sepan de qué historia
      // estamos hablando

      final SttDbAdapter dbAdapter = new SttDbAdapter(this);
      HistoryLoaderService loader = new HistoryLoaderService(dbAdapter);

      final History his = loader.fullLoadHistory();
      dbAdapter.close();

      return his;
   }

   /**
    * @deprecated
    * @return Page to show when not history
    */
   public Page getDefaultPage() {
      final Page page = new Page();
      page.setId(History.INIT_PAGE_ID);
      page.setTextHistory(this.getString(R.string.default_text_history));
      // page.checkOptionList = Collections.EMPTY_LIST;
      return page;
   }

   /**
    * Muestra la Page actual en la pantalla.
    */
   private void refrescar() {
      final Page page = Game.getInstance().getCurrentPage();
      this.historyText.setText(page.getTextHistory());
      // this.optionsListView.

      checkOptionAdapter.clear();

      // Populate the list, through the adapter
      for (final Option entry : page.getOptionList()) {
         if (entry.isNotHidden()) {
            checkOptionAdapter.add(entry);
         }
      }
   }

   /**
    * Escucha los click en las View de los option. Selecciona la opción en el Game y actualiza la pantalla.
    * 
    * @author BIHEALGA
    * 
    */
   private class OptionOnClickListener implements OnClickListener {

      @Override
      public void onClick(View v) {
         final Option opcion = (Option) v.getTag();
         Game.getInstance().choose(opcion);
         StoryTellerMAC.this.refrescar();
      }

   }

   /**
    * 
    * @param className = "android.app.ActivityThread"
    * @return
    */
   private Application getClass(String className) {
      Application lclass = null;
      try {
         final Class<?> activityThreadClass = Class.forName(className);
         final Method method = activityThreadClass.getMethod("currentApplication");
         lclass = (Application) method.invoke(null, (Object[]) null);
      } catch (final ClassNotFoundException e) {
         // handle exception
      } catch (final NoSuchMethodException e) {
         // handle exception
      } catch (final IllegalArgumentException e) {
         // handle exception
      } catch (final IllegalAccessException e) {
         // handle exception
      } catch (final InvocationTargetException e) {
         // handle exception
      }
      return lclass;
   }
}

/**
 * 
 */
package org.stt.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

/**
 * @author Gaizka
 *
 */
public class SttAssetsService {
	public static final String FILE_CREATE = "sql/ddl/sttCreate_v01.sql";
	public static final String FILE_DROP = "sql/ddl/sttDrop.sql";
	public static final String FILE_HISTORY_00000001 = "sql/data/sttHis_00000001.sql";
	
	private AssetManager am;
	private final Context context;
	
	public SttAssetsService(Context context){
		this.context = context;
	}
	
	private AssetManager getAssetManager(){
		if(am == null){
			am = context.getAssets();
		}
		return am;
	}
	
	/**
	 * Devuelve un iterable para recorrer las líneas del fichero
	 * @param path
	 * @return
	 */
	public Iterator<String> getLineToLine(String path){
//		Log.v(this.getClass().getSimpleName(), "getLineToLine() "+ path);

		final InputStream is;
		final Iterator<String> it;
		try {
			is = this.getAssetManager().open(path);
			it = this.convertStreamToIterable(is);
		} catch (IOException e) {
			final String msg = "getStringFromAssetFile Fallo de lectura de fichero "+path;
			Log.e(this.getClass().getName(), msg, e);
			throw new RuntimeException(msg, e);
		}
		return it;
	}
	/**
	 * Convierte un asset en un string
	 * @param path direccion en asset
	 * @return
	 */
	public String getStringFromAssetFile(String path) {
		InputStream is;
		String s;
		try {
			is = this.getAssetManager().open(path);
			s = convertStreamToString(is);
			is.close();
		} catch (IOException e) {
			final String msg = "getStringFromAssetFile Fallo de lectura de fichero "+path;
			Log.e(this.getClass().getName(), msg, e);
			throw new RuntimeException(msg, e);
		}
		return s;
	}

	private String convertStreamToString(InputStream is) throws IOException {
        final StringBuilder inputStringBuilder = new StringBuilder();
        final InputStreamReader isr = new InputStreamReader(is);
        final BufferedReader bufferedReader = new BufferedReader(isr);
        String line = bufferedReader.readLine();
        while(line != null){
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        isr.close();
        
		return inputStringBuilder.toString();
	}
	
	private Iterator<String> convertStreamToIterable(InputStream is){
		return new BufferReaderIterator(is);
	}
	/**
	 * Clase que itera cada linea de un InputStream hasta que al terminar lo cierra.
	 * Se usa interando hasta que no queden más líneas
	 * @author Gaizka
	 *
	 */
	public static class BufferReaderIterator implements Iterator<String>{
		final InputStream is;
        final InputStreamReader isr;
        final BufferedReader bufferedReader;
        boolean abierto;
        String line;
        
		public BufferReaderIterator(InputStream is){
			this.is = is;
	        isr = new InputStreamReader(is);
	        bufferedReader = new BufferedReader(isr);
	        abierto = true;
	        this.next();//para cargar la línea
		}
		@Override
		public boolean hasNext() {
			if(!abierto){
				return false;
			}
			boolean resultado = line != null;
			if(!resultado){
				try {
					bufferedReader.close();
					isr.close();
					is.close();
				} catch (IOException e) {
					Log.e("SttAssetsUtil", "Error Cerrando asset en BufferReaderIterator", e);
					throw new RuntimeException("Cerrando asset en BufferReaderIterator: " +e.getMessage());
				}
			}
			return resultado;
		}

		@Override
		public String next() {
			final String cpLine = line;
			try{
				line = bufferedReader.readLine();
			} catch (IOException e) {
				Log.e("SttAssetsUtil", "Error leyendo asset BufferReaderIterator", e);
				throw new RuntimeException("Leyendo asset en BufferReaderIterator: " +e.getMessage());
			}
			return cpLine;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	/**
	 * Devuelve una lista de las líneas del fichero, separadas por salto de línea
	 * @param is
	 * @return
	 * @throws IOException
	 */
//	private List<String> convertStreamToList(InputStream is) throws IOException{
//        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
//        final ArrayList<String> listaLineas = new ArrayList();
//        String line = bufferedReader.readLine();
//        while(line != null){
//        	listaLineas.add(line);
//            line = bufferedReader.readLine();
//        }
//        bufferedReader.close();
//        
//		return listaLineas;
//	}
	
	/** Cierra el assetManager */
	public void close(){
		if(am != null){
			am.close();
			am = null;
		}
	}
}

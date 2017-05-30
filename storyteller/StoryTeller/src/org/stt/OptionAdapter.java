package org.stt;

import org.stt.model.history.Option;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import org.stt.R;

/**
 * @see http://www.learn-android.com/2011/11/22/lots-of-lists-custom-adapter/3/
 * @author BIHEALGA
 *
 */
public class OptionAdapter extends ArrayAdapter<Option> {
	private OnClickListener optionListener;
	private final int checkOptionLayoutResource;
	
	public OptionAdapter(final Context context, final int newsItemLayoutResource) {
		super(context, newsItemLayoutResource);
		this.checkOptionLayoutResource = newsItemLayoutResource;
	}
	
	public void setOptionListener(OnClickListener optionListener){
		this.optionListener = optionListener;
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// We need to get the best view (re-used if possible) and then
		// retrieve its corresponding ViewHolder, which optimizes lookup efficiency
		final View view = getWorkingView(convertView);
		final ViewHolder viewHolder = getViewHolder(view);
		final Option entry = getItem(position);

		// Setting the title view is straightforward
		viewHolder.button.setText(entry.getDescription());
		viewHolder.button.setTag(entry);

		return view;
	}
	

	private View getWorkingView(final View convertView) {
		// The workingView is basically just the convertView re-used if possible
		// or inflated new if not possible
		View workingView = null;

		if(convertView == null) {
			final Context context = getContext();
			final LayoutInflater inflater = (LayoutInflater)context.getSystemService
		      (Context.LAYOUT_INFLATER_SERVICE);

			workingView = inflater.inflate(checkOptionLayoutResource, null);
		} else {
			workingView = convertView;
		}

		return workingView;
	}

	private ViewHolder getViewHolder(final View workingView) {
		// The viewHolder allows us to avoid re-looking up view references
		// Since views are recycled, these references will never change
		final Object tag = workingView.getTag();
		ViewHolder viewHolder = null;


		if(null == tag || !(tag instanceof ViewHolder)) {
			viewHolder = new ViewHolder();

			viewHolder.button = (Button) workingView.findViewById(R.id.checkOptionButton);
			viewHolder.button.setOnClickListener(this.optionListener);
			

			workingView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) tag;
		}

		return viewHolder;
	}
	
	/**
	 * ViewHolder allows us to avoid re-looking up view references
	 * Since views are recycled, these references will never change
	 */
	private static class ViewHolder {
		public Button button;
	}
}

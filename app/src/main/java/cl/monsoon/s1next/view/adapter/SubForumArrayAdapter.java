package cl.monsoon.s1next.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cl.monsoon.s1next.App;
import cl.monsoon.s1next.data.api.model.Forum;
import cl.monsoon.s1next.util.ViewUtil;

public final class SubForumArrayAdapter extends ArrayAdapter<Forum> {

    @LayoutRes
    private final int mResource;

    private final int mSecondaryTextColor;

    public SubForumArrayAdapter(Context context, @LayoutRes int resource, List<Forum> objects) {
        super(context, resource, objects);

        this.mResource = resource;
        mSecondaryTextColor = App.getAppComponent(context).getThemeManager().getSecondaryTextColor();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mResource, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Forum forum = getItem(position);

        TextView textView = viewHolder.textView;
        textView.setText(forum.getName());
        // add today's posts count to each forum
        if (forum.getTodayPosts() != 0) {
            ViewUtil.concatWithTwoSpacesForRtlSupport(textView, String.valueOf(forum.getTodayPosts()),
                    mSecondaryTextColor);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(getItem(position).getId());
    }

    private static class ViewHolder {

        private TextView textView;
    }
}

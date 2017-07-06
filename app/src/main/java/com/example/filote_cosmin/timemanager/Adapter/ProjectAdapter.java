package com.example.filote_cosmin.timemanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.filote_cosmin.timemanager.Activities.Project;
import com.example.filote_cosmin.timemanager.Model.Proiect;
import com.example.filote_cosmin.timemanager.R;

import java.util.List;

/**
 * Created by Filote Cosmin on 7/5/2017.
 */

public class ProjectAdapter extends BaseAdapter{

    private List<Proiect> mProjectList;
    private Context mContext;

    public ProjectAdapter(List<Proiect> mProjectList, Context mContext) {
        this.mProjectList = mProjectList;
        this.mContext = mContext;
    }

    //get the number of the list
    @Override
    public int getCount() {
        if (mProjectList == null)
            return 0;
        else
            return mProjectList.size();
    }

    //get the object from a specified position
    @Override
    public Object getItem(int position) {
        if (mProjectList == null)
            return null;
        else
            return mProjectList.get(position);
    }

    //get the item it from a specified position
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //build the view of the item from a specified position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        final int currentPosition = position;
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            int layoutId = R.layout.project_item;
            view = layoutInflater.inflate(layoutId, parent, false);
            viewHolder = new ProjectAdapter.ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ProjectAdapter.ViewHolder) view.getTag();
        }

        Proiect project = (Proiect) getItem(currentPosition);
        viewHolder.mProjectTextView.setText(project.getNume());

        return view;


    }
    //Design pattern ViewHolder

    class ViewHolder {
        protected TextView mProjectTextView;

        public ViewHolder(View view){
            mProjectTextView = (TextView) view.findViewById(R.id.tv_projectAdapter);
        }
    }

}

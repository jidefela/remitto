package co.carpware.remitto.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import co.carpware.remitto.R;
import co.carpware.remitto.model.User;

/**
 * Created by jide on 26/07/17.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.UserViewHolder> {

    private List<User> mUsers;
    private LinkedHashMap<String, Integer> mMapIndex;
    private ArrayList<String> mSectionList;
    private String[] mSections;

    public ContactsAdapter(Context pContext, List<User> user) {
        //mUsers = UsersProvider.load(pContext);
        mUsers = new ArrayList<>();
        mUsers.add(new User(R.drawable.user, "Helen Kelley"));
        mUsers.add(new User(R.drawable.friend_3, "Tammy Schmidt"));
        mUsers.add(new User(R.drawable.friend_8, "Amar Sagoo"));
        mUsers.add(new User(R.drawable.friend_3, "Tammy Schmidt"));
        mUsers.add(new User(R.drawable.friend_3, "Aajanle4334"));
        mUsers.add(new User(R.drawable.friend_8, "Borokinin"));
        mUsers.add(new User(R.drawable.user, "Budweiser"));
        mUsers.add(new User(R.drawable.friend_3, "Tammy Schmidt"));
        mUsers.add(new User(R.drawable.friend_3, "Aajanle4334"));
        mUsers.add(new User(R.drawable.friend_8, "Borokinin"));
        mUsers.add(new User(R.drawable.user, "Budweiser"));
        mUsers.add(new User(R.drawable.user, "Helen Kelley"));
        mUsers.add(new User(R.drawable.friend_3, "Tammy Schmidt"));
        mUsers.add(new User(R.drawable.friend_8, "Amar Sagoo"));
        mUsers.add(new User(R.drawable.friend_3, "Tammy Schmidt"));
         //mUsers.addAll(user);
        fillSections();
    }

    private void fillSections() {
        mMapIndex = new LinkedHashMap<String, Integer>();

        for (int x = 0; x < mUsers.size(); x++) {
            String fruit = mUsers.get(x).getName();
            if (fruit.length() > 1) {
                String ch = fruit.substring(0, 1);
                ch = ch.toUpperCase();
                if (!mMapIndex.containsKey(ch)) {
                    mMapIndex.put(ch, x);
                }
            }
        }
        Set<String> sectionLetters = mMapIndex.keySet();
        // create a list from the set to sort
        mSectionList = new ArrayList<String>(sectionLetters);
        Collections.sort(mSectionList);

        mSections = new String[mSectionList.size()];
        mSectionList.toArray(mSections);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View content = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contact, null);
        return new UserViewHolder(content);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User lUser = getItem(position);
        String section = getSection(lUser);
        holder.bind(lUser, section, mMapIndex.get(section) == position);
    }

    @NonNull
    private String getSection(User pUser) {
        return pUser.getName().substring(0, 1).toUpperCase();
    }

    private User getItem(int pPosition) {
        return mUsers.get(pPosition);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView mName;
        private final TextView mSectionName;

        public UserViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.text_name);
            mSectionName = (TextView) itemView.findViewById(R.id.section_title);
        }

        public void bind(User pItem, String pSection, boolean bShowSection) {
            mName.setText(pItem.getName());
            mSectionName.setText(pSection);
            mSectionName.setVisibility(bShowSection ? View.VISIBLE : View.GONE);
        }
    }
}

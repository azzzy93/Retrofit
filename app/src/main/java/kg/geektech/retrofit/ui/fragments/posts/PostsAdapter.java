package kg.geektech.retrofit.ui.fragments.posts;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kg.geektech.retrofit.databinding.ListPostsBinding;
import kg.geektech.retrofit.interfaces.MyItemClickListeners;
import kg.geektech.retrofit.models.Post;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {
    private ListPostsBinding binding;
    private List<Post> posts;
    private MyItemClickListeners myItemClickListeners;

    public PostsAdapter() {
        posts = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ListPostsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PostsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        holder.onBind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setMyItemClickListeners(MyItemClickListeners myItemClickListeners) {
        this.myItemClickListeners = myItemClickListeners;
    }

    public Post getPost(int position){
        return posts.get(position);
    }

    public void removePost(int position){
        posts.remove(position);
        notifyItemRemoved(position);
    }















    public class PostsViewHolder extends RecyclerView.ViewHolder {
        private ListPostsBinding binding;

        public PostsViewHolder(@NonNull ListPostsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            itemView.setOnClickListener(v -> {
                myItemClickListeners.onClick(getAdapterPosition());
            });

            itemView.setOnLongClickListener(v -> {
                myItemClickListeners.onLongClick(getAdapterPosition());
                return true;
            });
        }

        public void onBind(Post post) {
            String userName = "User name: " + getUserName(post.getUserId());
            String groupId = "Group ID: " + post.getGroupId();
            String id = "ID: " + post.getId();

            binding.tvUser.setText(userName);
            binding.tvTitle.setText(post.getTitle());
            binding.tvContent.setText(post.getContent());
            binding.tvGroup.setText(groupId);
            binding.tvId.setText(id);
        }

        private String getUserName(Integer userId) {
            Map<Integer, String> getUserName = new HashMap<>();
            getUserName.put(0, "Учитель");
            getUserName.put(1, "Арслан");
            getUserName.put(2, "Шамсуддин");
            getUserName.put(3, "Азизбек");
            getUserName.put(4, "Жаркынай");
            getUserName.put(5, "Дастан Н.");
            getUserName.put(6, "Нурсамад");
            getUserName.put(7, "Дастан К.");
            getUserName.put(8, "Евгения");
            getUserName.put(9, "Анипа");
            return getUserName.get(userId);
        }
    }
}

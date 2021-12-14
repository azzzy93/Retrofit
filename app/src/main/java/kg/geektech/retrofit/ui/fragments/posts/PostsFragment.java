package kg.geektech.retrofit.ui.fragments.posts;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.List;

import kg.geektech.retrofit.App;
import kg.geektech.retrofit.R;
import kg.geektech.retrofit.databinding.FragmentPostsBinding;
import kg.geektech.retrofit.interfaces.MyItemClickListeners;
import kg.geektech.retrofit.models.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsFragment extends Fragment {
    private FragmentPostsBinding binding;
    private PostsAdapter adapter;
    private final static Integer USER_ID = 3;
    private final static Integer GROUP_ID = 36;
    private Post post;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PostsAdapter();
        adapter.setMyItemClickListeners(new MyItemClickListeners() {
            @Override
            public void onClick(Integer position) {
                if (adapter.getPost(position).getUserId().equals(USER_ID)) {
                    post = adapter.getPost(position);
                    binding.etTitle.setText(post.getTitle());
                    binding.etContent.setText(post.getContent());
                } else {
                    Toast.makeText(requireContext(), "Это не ваш пост, вы не можете его редактировать", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongClick(Integer position) {
                if (adapter.getPost(position).getUserId().equals(USER_ID)) {
                    post = adapter.getPost(position);
                    App.api.deletePost(post.getId()).enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            adapter.removePost(position);
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {

                        }
                    });
                } else {
                    Toast.makeText(requireContext(), "Это не ваш пост, вы не можете его удалить", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getData();
        initListeners();
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(requireActivity().getCurrentFocus().getWindowToken(), 0);
    }

    private void initListeners() {
        binding.btnSend.setOnClickListener(v -> {
            sendData();
        });
    }

    private void sendData() {
        String title = binding.etTitle.getText().toString().trim();
        String content = binding.etContent.getText().toString().trim();

        if (!title.isEmpty() && !content.isEmpty()) {
            if (post == null) {
                post = new Post(title, content, USER_ID, GROUP_ID);
                App.api.setPost(post).enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        binding.etTitle.setText("");
                        binding.etContent.setText("");
                        hideKeyboard();
                        getData();
                        post = null;
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {

                    }
                });
            } else {
                post.setTitle(title);
                post.setContent(content);
                App.api.updatePost(post.getId(), post).enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        binding.etTitle.setText("");
                        binding.etContent.setText("");
                        hideKeyboard();
                        getData();
                        post = null;
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {

                    }
                });
            }
        } else {
            Toast.makeText(requireContext(), "Введите текст", Toast.LENGTH_SHORT).show();
        }
    }

    private void getData() {
        App.api.getPosts(GROUP_ID).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                adapter.setPosts(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
        binding.rvPosts.setAdapter(adapter);
    }
}
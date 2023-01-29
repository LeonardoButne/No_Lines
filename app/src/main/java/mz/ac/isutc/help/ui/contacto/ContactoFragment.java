package mz.ac.isutc.help.ui.contacto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.proto.ProtoOutputStream;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mz.ac.isutc.help.R;
import mz.ac.isutc.help.databinding.FragmentContactoBinding;


public class ContactoFragment extends Fragment {
    private FragmentContactoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContactoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}
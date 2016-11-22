package xyz.discoverandroid.eventbustutorial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import xyz.discoverandroid.eventbustutorial.Events.ChangeImageEvent;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeImageFragment extends Fragment implements View.OnClickListener {
    private Button nextButton, previousButton;
    private EventBus eventBus = EventBus.getDefault();

    public ChangeImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_image, container, false);
        nextButton = (Button) view.findViewById(R.id.next_button);
        previousButton = (Button) view.findViewById(R.id.previous_button);
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_button:
                eventBus.post(new ChangeImageEvent("next"));
                break;
            case R.id.previous_button:
                eventBus.post(new ChangeImageEvent("prev"));
                break;
        }
    }
}

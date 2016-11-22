package xyz.discoverandroid.eventbustutorial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xyz.discoverandroid.eventbustutorial.Events.ChangeImageEvent;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {

    private ImageView imageView;
    private int imageCounter = 2;
    public int[] images = {R.drawable.mercury, R.drawable.venus, R.drawable.earth, R.drawable.mars, R.drawable.jupiter, R.drawable.saturn, R.drawable.uranus, R.drawable.neptune};

    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        imageView = (ImageView) view.findViewById(R.id.image_view);
        setImageSrc(imageCounter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //Register EventBus
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        //Unregister EventBus
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ChangeImageEvent event) {
        if (event.changeImage.equals("next")) {
            imageCounter++;
            imageCounter = imageCounter % images.length;
            setImageSrc(imageCounter);
        } else if (event.changeImage.equals("prev")) {
            if (imageCounter == 0) {
                imageCounter = images.length;
            }
            imageCounter--;
            imageCounter = imageCounter % images.length;
            setImageSrc(imageCounter);
        }
    }

    private void setImageSrc(int imageCounter) {
        imageView.setImageResource(images[imageCounter]);
    }

}

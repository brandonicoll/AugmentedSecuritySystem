package augmented.security.asecuritysystem.ui.DoorLock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LockViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LockViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
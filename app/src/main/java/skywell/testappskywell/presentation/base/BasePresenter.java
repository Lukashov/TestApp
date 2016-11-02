package skywell.testappskywell.presentation.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Den on 31.10.16.
 */

public interface BasePresenter {
    void subscribe();
    void unSubscribe();
}

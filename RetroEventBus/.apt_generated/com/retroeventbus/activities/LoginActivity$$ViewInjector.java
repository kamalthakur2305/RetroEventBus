// Generated code from Butter Knife. Do not modify!
package com.retroeventbus.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class LoginActivity$$ViewInjector<T extends com.retroeventbus.activities.LoginActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296320, "field 'textView'");
    target.textView = finder.castView(view, 2131296320, "field 'textView'");
    view = finder.findRequiredView(source, 2131296319, "field 'start_button' and method 'start'");
    target.start_button = finder.castView(view, 2131296319, "field 'start_button'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.start();
        }
      });
  }

  @Override public void reset(T target) {
    target.textView = null;
    target.start_button = null;
  }
}

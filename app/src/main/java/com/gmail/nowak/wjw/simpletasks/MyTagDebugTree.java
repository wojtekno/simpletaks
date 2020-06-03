package com.gmail.nowak.wjw.simpletasks;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public class MyTagDebugTree extends Timber.DebugTree {

    @Override
    protected void log(int priority, String tag, @NotNull String message, Throwable t) {
        super.log(priority, "MY_TAG_".concat(tag), message, t);
    }
}

package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.util.Collections;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
final class Absent<T> extends Optional<T> {
    static final Absent<Object> INSTANCE = new Absent<>();
    private static final long serialVersionUID = 0;

    static <T> Optional<T> withType() {
        return INSTANCE;
    }

    private Absent() {
    }

    public boolean isPresent() {
        return false;
    }

    public T get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    /* renamed from: or */
    public T mo14335or(T defaultValue) {
        return Preconditions.checkNotNull(defaultValue, "use Optional.orNull() instead of Optional.or(null)");
    }

    /* renamed from: or */
    public Optional<T> mo14333or(Optional<? extends T> secondChoice) {
        return (Optional) Preconditions.checkNotNull(secondChoice);
    }

    /* renamed from: or */
    public T mo14334or(Supplier<? extends T> supplier) {
        return Preconditions.checkNotNull(supplier.get(), "use Optional.orNull() instead of a Supplier that returns null");
    }

    @Nullable
    public T orNull() {
        return null;
    }

    public Set<T> asSet() {
        return Collections.emptySet();
    }

    public <V> Optional<V> transform(Function<? super T, V> function) {
        Preconditions.checkNotNull(function);
        return Optional.absent();
    }

    public boolean equals(@Nullable Object object) {
        return object == this;
    }

    public int hashCode() {
        return 1502476572;
    }

    public String toString() {
        return "Optional.absent()";
    }

    private Object readResolve() {
        return INSTANCE;
    }
}

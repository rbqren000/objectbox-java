package io.objectbox.tree;

import io.objectbox.BoxStore;

import javax.annotation.Nullable;

/**
 * Points to a root branch, can traverse child branches and read and write data in leafs.
 */
public class Tree {

    private final BoxStore store;
    @Nullable
    private final String uid;
    private long handle;

    public Tree(BoxStore store, @Nullable String uid) {
        this.store = store;
        this.uid = uid;
        this.handle = nativeCreate(uid);
    }

    public Branch root() {
        throw new UnsupportedOperationException();
    }

    public void close() {
        long handle = this.handle;
        nativeDelete(handle);
        this.handle = 0;
    }

    private static native long nativeCreate(@Nullable String uid);

    private static native void nativeDelete(long handle);

    public static class Branch {

        public Branch branch(String[] path) {
            throw new UnsupportedOperationException();
        }

        public Branch branch(String name) {
            return branch(new String[]{name});
        }

        public Leaf leaf(String[] path) {
            throw new UnsupportedOperationException();
        }

        public Leaf leaf(String name) {
            return leaf(new String[]{name});
        }

    }

    public static class Leaf {

        public boolean isInt() {
            throw new UnsupportedOperationException();
        }

        public boolean isDouble() {
            throw new UnsupportedOperationException();
        }

        public boolean isString() {
            throw new UnsupportedOperationException();
        }

        public boolean isStringArray() {
            throw new UnsupportedOperationException();
        }

        // valueInt
        @Nullable
        public Long asInt() {
            throw new UnsupportedOperationException();
        }

        // valueDouble
        @Nullable
        public Double asDouble() {
            throw new UnsupportedOperationException();
        }

        // valueString
        @Nullable
        public String asString() {
            throw new UnsupportedOperationException();
        }

        // valueStrings
        @Nullable
        public String[] asStringArray() {
            throw new UnsupportedOperationException();
        }

        public void setInt(@Nullable Long value) {
            throw new UnsupportedOperationException();
        }

        public void setDouble(@Nullable Double value) {
            throw new UnsupportedOperationException();
        }

        public void setString(@Nullable String value) {
            throw new UnsupportedOperationException();
        }

        public void setStringArray(@Nullable String[] value) {
            throw new UnsupportedOperationException();
        }

    }

}

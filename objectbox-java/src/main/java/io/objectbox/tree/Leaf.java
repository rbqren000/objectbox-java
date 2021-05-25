package io.objectbox.tree;

import io.objectbox.annotation.apihint.Internal;
import io.objectbox.model.PropertyType;

import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;

public class Leaf {

    private final LeafNode node;

    public Leaf(LeafNode node) {
        this.node = node;
    }

    // FIXME Remove for final API once it is clear how to get value type.
    /**
     * For testing purposes only.
     */
    @Internal
    public LeafNode getNode() {
        return node;
    }

    public boolean isInt() {
        return node.valueType == PropertyType.Long;
    }

    public boolean isDouble() {
        return node.valueType == PropertyType.Double;
    }

    public boolean isString() {
        return node.valueType == PropertyType.ByteVector;
    }

    public boolean isBytes() {
        return node.valueType == PropertyType.ByteVector;
    }

    public boolean isStringArray() {
        return node.valueType == PropertyType.ShortVector;
    }

    // valueInt
    @Nullable
    public Long getInt() {
        if (!isInt()) throw new IllegalStateException("value is not integer");
        return node.integerValue;
    }

    // valueDouble
    @Nullable
    public Double getDouble() {
        if (!isDouble()) throw new IllegalStateException("value is not floating point");
        return node.floatingValue;
    }

    // valueString
    @Nullable
    public String getString() {
        if (!isString()) throw new IllegalStateException("value is not string");
        if (node.objectValue instanceof String) {
            return (String) node.objectValue;
        } else {
            byte[] bytes = (byte[]) node.objectValue;
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }

    // valueStrings
    @Nullable
    public String[] getStringArray() {
        if (!isStringArray()) throw new IllegalStateException("value is not string array");
        return (String[]) node.objectValue;
    }

    @Nullable
    public Long asInt() {
        if (isInt()) return getInt();

        if (isDouble()) {
            Double value = getDouble();
            return value != null ? value.longValue() : null;
        }
        if (isString()) {
            String value = getString();
            return value != null ? Long.valueOf(value) : null;
        }

        return null;
    }

    @Nullable
    public Double asDouble() {
        if (isDouble()) return getDouble();

        if (isInt()) {
            Long value = getInt();
            return value != null ? value.doubleValue() : null;
        }
        if (isString()) {
            String value = getString();
            return value != null ? Double.valueOf(value) : null;
        }

        return null;
    }

    @Nullable
    public String asString() {
        if (isString()) return getString();

        if (isInt()) {
            Long value = getInt();
            return value != null ? String.valueOf(value) : null;
        }
        if (isDouble()) {
            Double value = getDouble();
            return value != null ? String.valueOf(value) : null;
        }
        if (isStringArray()) {
            // Return first item.
            String[] value = getStringArray();
            return value != null && value.length > 0 ? value[0] : null;
        }

        return null;
    }

    @Nullable
    public String[] asStringArray() {
        if (isStringArray()) return getStringArray();

        if (isInt()) {
            Long value = getInt();
            return value != null ? new String[]{String.valueOf(value)} : null;
        }
        if (isDouble()) {
            Double value = getDouble();
            return value != null ? new String[]{String.valueOf(value)} : null;
        }
        if (isString()) {
            String value = getString();
            return value != null ? new String[]{value} : null;
        }

        return null;
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

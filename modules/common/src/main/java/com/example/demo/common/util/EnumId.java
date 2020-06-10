package com.example.demo.common.util;

import java.util.Optional;

public interface EnumId {

    String name();

    default String getId() {
        return name();
    }

    static <T extends Enum<?> & EnumId> T parseName(Class<T> cls, String name) {
        T res = parseName(cls.getEnumConstants(), name, null);

        if (res != null) {
            return res;
        }

        throw new EnumConstantNotPresentException(cls, name);
    }

    static <T extends Enum<?> & EnumId> T parseName(Class<T> cls, String name, T def) {
        return parseName(cls.getEnumConstants(), name, def);
    }

    static <T extends EnumId> T parseName(T[] values, String name, T def) {
        for (T value : values) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }
        }

        return def;
    }

    static <T extends Enum<?> & EnumId> T parseIdOrName(Class<T> cls, String idOrName) {
        T res = parseId(cls.getEnumConstants(), idOrName, null);
        return Optional.ofNullable(res).orElseGet(() -> parseName(cls, idOrName));
    }

    static <T extends Enum<?> & EnumId> T parseId(Class<T> cls, String id) {
        T res = parseId(cls.getEnumConstants(), id, null);
        return Optional.ofNullable(res).orElseThrow(() -> new EnumConstantNotPresentException(cls, id));
    }

    static <T extends Enum<?> & EnumId> T parseId(Class<T> cls, String id, T def) {
        return parseId(cls.getEnumConstants(), id, def);
    }

    static <T extends EnumId> T parseId(T[] values, String id) {
        T result = parseId(values, id, null);
        if (result == null) {
            throw new IllegalArgumentException("Enum constant not present: " + id);
        }
        return result;
    }

    static <T extends EnumId> T parseId(T[] values, String id, T def) {
        for (T value : values) {
            if (id != null ? id.equalsIgnoreCase(value.getId()) : value.getId() == null) {
                return value;
            }
        }
        return def;
    }

    static <T extends EnumId> T get(T value, T def) {
        return value != null ? value : def;
    }

    static <T extends EnumId> String getIdOf(T value) {
        return value != null ? value.getId() : null;
    }

    static <T extends EnumId> String getName(T value) {
        return value != null ? value.name() : null;
    }

    static String getEnumId(EnumId enumId) {
        return enumId == null ? "null" : enumId.getId();
    }
}

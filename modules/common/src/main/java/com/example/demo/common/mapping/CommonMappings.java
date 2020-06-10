package com.example.demo.common.mapping;

import java.util.UUID;

@SuppressWarnings({"InterfaceNeverImplemented", "unused"})
public interface CommonMappings {

    default String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    default UUID stringToUUID(String uuid) {
        return uuid != null ? UUID.fromString(uuid) : null;
    }
}

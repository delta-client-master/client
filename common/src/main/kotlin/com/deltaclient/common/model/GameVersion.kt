package com.deltaclient.common.model

enum class GameVersion(val displayName: String) {
    v1_7_10("1.7.10"),
    v1_8_9("1.8.9"),
    v1_18_1("1.18.1");

    override fun toString(): String {
        return displayName
    }
}
package com.deltaclient.common.model

enum class GameVersion(val displayName: String) {
    V1_7_10("1.7.10"),
    V1_8_9("1.8.9"),
    V1_18_1("1.18.1");

    override fun toString(): String {
        return displayName
    }
}
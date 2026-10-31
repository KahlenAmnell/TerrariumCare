package com.example.terrariumcare.data.local.converters

import androidx.room.TypeConverter
import com.example.terrariumcare.data.local.enums.CareEventType
import com.example.terrariumcare.data.local.enums.EntryType

class EnumConverters {
    @TypeConverter
    fun fromEntryType(entryType: EntryType?): String? {
        return entryType?.name
    }

    @TypeConverter
    fun toEntryType(value: String?): EntryType? {
        return value?.let {
            try {
                enumValueOf<EntryType>(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }

    @TypeConverter
    fun fromCareEventType(careEventType: CareEventType?): String? {
        return careEventType?.name
    }

    @TypeConverter
    fun toCareEventType(value: String?): CareEventType? {
        return value?.let {
            try {
                enumValueOf<CareEventType>(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
}

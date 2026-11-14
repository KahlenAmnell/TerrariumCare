package com.example.terrariumcare.utils

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

interface ImageStorageManager {
    fun saveImageToInternalStorage(sourceUriString: String): String?
    fun deleteImageFromInternalStorage(filePath: String?): Boolean
}

@Singleton
class ImageStorageManagerImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : ImageStorageManager {

    override fun saveImageToInternalStorage(sourceUriString: String): String? {
        if (sourceUriString.isBlank()) return null
        return try {
            val sourceUri = Uri.parse(sourceUriString)
            val internalImagesDir = getInternalImagesDir()

            val fileName = "img_${UUID.randomUUID()}.jpg"
            val targetFile = File(internalImagesDir, fileName)

            context.contentResolver.openInputStream(sourceUri)?.use { inputStream ->
                targetFile.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            } ?: return null

            targetFile.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun deleteImageFromInternalStorage(filePath: String?): Boolean {
        if (filePath.isNullOrBlank()) return false
        return try {
            val file = File(filePath)
            if (file.exists()) file.delete() else false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun getInternalImagesDir(): File {
        val internalImagesDir = File(context.filesDir, "images")
        if (!internalImagesDir.exists()) {
            internalImagesDir.mkdirs()
        }
        return internalImagesDir
    }
}

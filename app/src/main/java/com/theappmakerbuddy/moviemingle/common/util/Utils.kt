package com.theappmakerbuddy.moviemingle.common.util

import android.content.Context

/*
actual val versionName: String
        get() = try {
            val pInfo: PackageInfo =
                context.packageManager.getPackageInfo(context.packageName, 0)
            val version = pInfo.versionName
            version
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            "---"
        }

    actual val versionCode: Int
        get() = try {
            val pInfo: PackageInfo =
                context.packageManager.getPackageInfo(context.packageName, 0)
            val version = pInfo.versionCode
            version
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            0
        }
 */

fun Context.appVersionName(): String {
    return try {
        val pInfo = this.packageManager.getPackageInfo(this.packageName, 0)
        val version = pInfo.versionName
        version
    } catch (e: Exception) {
        e.printStackTrace()
        "---"
    }
}

fun Context.appVersionCode(): Int {
    return try {
        val pInfo = this.packageManager.getPackageInfo(this.packageName, 0)
        val version = pInfo.versionCode
        version
    } catch (e: Exception) {
        e.printStackTrace()
        0
    }
}

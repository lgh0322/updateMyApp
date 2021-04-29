package com.vaca.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File


class MainActivity : AppCompatActivity() {
    val INSTALL_PERMISS_CODE = 1 //安装未知来源apk权限

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PathUtil.initVar(this)

        val haveInstallPermission = packageManager.canRequestPackageInstalls();
        if(!haveInstallPermission){
            setInstallPermission()
        }else{
            installApk(
                    File(PathUtil.getPathX("app-debug.apk"))
            )
        }




    }

    fun setInstallPermission() {
        val haveInstallPermission: Boolean
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //先判断是否有安装未知来源应用的权限
            haveInstallPermission = packageManager.canRequestPackageInstalls()
            if (!haveInstallPermission) {
                //弹框提示用户手动打开
                MessageDialog.showAlert(this, "安装权限", "需要打开允许来自此来源，请去设置中开启此权限") { dialog, which ->
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        //此方法需要API>=26才能使用
                        toInstallPermissionSettingIntent()
                    }
                }
                return
            }
        }
    }

    private fun installApk(apk: File) {
        val intent = Intent(Intent.ACTION_VIEW)
        //Android7.0之后获取uri要用contentProvider
        val uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", apk)
        intent.setDataAndType(uri, "application/vnd.android.package-archive")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        baseContext.startActivity(intent)
    }

    /**
     * 开启安装未知来源权限
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun toInstallPermissionSettingIntent() {
        val packageURI = Uri.parse("package:$packageName")
        val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI)
        startActivityForResult(intent, INSTALL_PERMISS_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == INSTALL_PERMISS_CODE) {
            Toast.makeText(this, "安装应用", Toast.LENGTH_SHORT).show()

            installApk(
                    File(PathUtil.getPathX("app-debug.apk"))
            )

        }
    }
}
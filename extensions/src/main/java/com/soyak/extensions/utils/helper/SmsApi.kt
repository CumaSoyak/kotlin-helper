//package com.mobillium.birebirdiyet.utils.helper
//
//import android.app.Activity
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.content.IntentFilter
//import android.provider.FontsContract
//import com.google.android.gms.auth.api.phone.SmsRetriever
//import com.google.android.gms.common.api.CommonStatusCodes
//import com.google.android.gms.common.api.Status
//
//import java.lang.ref.WeakReference
//
//class SmsApi(activity: Activity) {
//
//    private val activityWeakReference = WeakReference<Activity>(activity)
//
//    private val mContext = activityWeakReference.get() as Activity
//
//    lateinit var smsBroadcastReceiver: SmsBroadcastReceiver
//
//    fun onActivityResult(
//        requestCode: Int,
//        resultCode: Int,
//        data: Intent?,
//        requestCallback: (code: String) -> Unit
//    ) {
//        when (requestCode) {
//            REQ_USER_CONSENT -> {
//                if ((resultCode == Activity.RESULT_OK) && (data != null)) {
//                    val message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
//                    val code = message?.let { fetchVerificationCode(it) }
//                    code?.let { requestCallback(code) }
//
//                }
//            }
//        }
//    }
//
//    fun startSmsUserConsent() {
//        SmsRetriever.getClient(activityWeakReference.get() as Activity).also {
//            //We can add user phone number or leave it blank
//            it.startSmsUserConsent(null)
//                .addOnSuccessListener {
//                    (activityWeakReference.get() as Activity).loggeri("LISTENING_SUCCESS")
//                }
//                .addOnFailureListener {
//                    (activityWeakReference.get() as Activity).loggeri("LISTENING_FAILURE")
//                }
//        }
//    }
//
//    fun registerToSmsBroadcastReceiver() {
//        smsBroadcastReceiver = SmsBroadcastReceiver().also {
//            it.smsBroadcastReceiverListener =
//                object : SmsBroadcastReceiver.SmsBroadcastReceiverListener {
//                    override fun onSuccess(intent: Intent?) {
//                        intent?.let { context ->
//                            (activityWeakReference.get() as Activity).startActivityForResult(
//                                intent,
//                                REQ_USER_CONSENT
//                            )
//                        }
//                    }
//
//                    override fun onFailure() {
//                        mContext.loggeri("onFailure->registerToSmsBroadcastReceiver")
//                    }
//                }
//        }
//
//        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
//        (activityWeakReference.get() as Activity).registerReceiver(
//            smsBroadcastReceiver,
//            intentFilter
//        )
//    }
//
//    private fun fetchVerificationCode(message: String): String {
//        return Regex("(\\d{6})").find(message)?.value ?: ""
//    }
//
//    companion object {
//        const val REQ_USER_CONSENT = 100
//    }
//}
//
//class SmsBroadcastReceiver : BroadcastReceiver() {
//
//    lateinit var smsBroadcastReceiverListener: SmsBroadcastReceiverListener
//
//    override fun onReceive(context: Context?, intent: Intent?) {
//
//        if (intent?.action == SmsRetriever.SMS_RETRIEVED_ACTION) {
//
//            val extras = intent.extras
//            val smsRetrieverStatus = extras?.get(SmsRetriever.EXTRA_STATUS) as Status
//
//            when (smsRetrieverStatus.statusCode) {
//                CommonStatusCodes.SUCCESS -> {
//                    extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT).also {
//                        smsBroadcastReceiverListener.onSuccess(it)
//                    }
//                }
//
//                CommonStatusCodes.TIMEOUT -> {
//                    smsBroadcastReceiverListener.onFailure()
//                }
//            }
//        }
//    }
//
//    interface SmsBroadcastReceiverListener {
//        fun onSuccess(intent: Intent?)
//        fun onFailure()
//    }
//}
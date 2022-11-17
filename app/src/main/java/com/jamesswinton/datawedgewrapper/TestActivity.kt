package com.jamesswinton.datawedgewrapper

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jamesswinton.datawedgewrapper.databinding.TestActivityBinding
import com.zebra.jamesswinton.datawedgewrapperlib.DataWedgeWrapper
import com.zebra.jamesswinton.datawedgewrapperlib.configuration.MainBundle
import com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins.BarcodePlugin
import com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins.WorkflowIPlugin
import com.zebra.jamesswinton.datawedgewrapperlib.models.barcode.BarcodeHighlightGenericRule
import com.zebra.jamesswinton.datawedgewrapperlib.models.barcode.BarcodeHighlightRuleAction
import com.zebra.jamesswinton.datawedgewrapperlib.models.barcode.BarcodeHighlightRuleCriteria
import com.zebra.jamesswinton.datawedgewrapperlib.models.barcode.BarcodeHighlightSymbology
import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.WorkflowInputMode
import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.WorkflowMode
import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.WorkflowOutputImageMode
import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules.*
import com.zebra.jamesswinton.datawedgewrapperlib.utilities.Constants

class TestActivity : AppCompatActivity() {

    private lateinit var mBinder: TestActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinder = TestActivityBinding.inflate(layoutInflater)
        setContentView(mBinder.root)

        fillUi()
    }

    private fun fillUi() {
        val demosAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.demos)
        )

        mBinder.demosInput.setAdapter(demosAdapter)
        mBinder.demosInput.onItemClickListener = demosClickListener
    }

    private val demosClickListener =
        AdapterView.OnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                    updateProfileWithBarcodeHighlightDemo()
                }
                1 -> {
                    updateProfileWithWorkflowLicenseDemo()
                }
                2 -> {
                    updateProfileWithWorkflowIdDemo()
                }
                3 -> {
                    updateProfileWithWorkflowVinDemo()
                }
                4 -> {
                    updateProfileWithWorkflowTinDemo()
                }
                5 -> {
                    updateProfileWithWorkflowAnalogMeterDemo()
                }
                6 -> {
                    updateProfileWithWorkflowDialMeterDemo()
                }
                7 -> {
                    updateProfileWithWorkflowContainerDemo()
                }
                8 -> {
                    updateProfileWithWorkflowFreeFormCaptureDemo()
                }
            }
        }

    private fun updateProfileWithBarcodeHighlightDemo() {
        val rule1 = BarcodeHighlightGenericRule("EAN8 Rule").apply {
            criteriaList.apply {
                add(
                    BarcodeHighlightRuleCriteria(
                        BarcodeHighlightRuleCriteria.Criteria.MAX_LENGTH,
                        "8"
                    )
                )
                add(
                    BarcodeHighlightRuleCriteria(
                        BarcodeHighlightRuleCriteria.Criteria.CONTAINS,
                        "0800"
                    )
                )
            }
            actionList.add(
                BarcodeHighlightRuleAction(
                    BarcodeHighlightRuleAction.Action.FILL_COLOR,
                    "#FFbc02d9"
                )
            )
        }

        val rule2 = BarcodeHighlightGenericRule("UPCA Rule").apply {
            criteriaList.apply {
                add(
                    BarcodeHighlightRuleCriteria(
                        BarcodeHighlightRuleCriteria.Criteria.MAX_LENGTH,
                        "12"
                    )
                )
                add(
                    BarcodeHighlightRuleCriteria(
                        BarcodeHighlightRuleCriteria.Criteria.CONTAINS,
                        "791000"
                    )
                )
            }
            actionList.add(
                BarcodeHighlightRuleAction(
                    BarcodeHighlightRuleAction.Action.FILL_COLOR,
                    "#FFedca02"
                )
            )
        }


        val symbologies = ArrayList<BarcodeHighlightSymbology>().apply {
            add(BarcodeHighlightSymbology.QRCODE)
        }
        val rule3 = BarcodeHighlightGenericRule("QRCode Rule").apply {
            actionList.add(
                BarcodeHighlightRuleAction(
                    BarcodeHighlightRuleAction.Action.FILL_COLOR,
                    "#FF44AACC"
                )
            )
            this.symbologies = symbologies
        }

        val reportRule = BarcodeHighlightGenericRule("ReportRule").apply {
            criteriaList.apply {
                add(
                    BarcodeHighlightRuleCriteria(
                        BarcodeHighlightRuleCriteria.Criteria.MAX_LENGTH,
                        "8"
                    )
                )
                add(
                    BarcodeHighlightRuleCriteria(
                        BarcodeHighlightRuleCriteria.Criteria.CONTAINS,
                        "0800"
                    )
                )
            }
            actionList.add(BarcodeHighlightRuleAction(BarcodeHighlightRuleAction.Action.REPORT))
        }

        // Create Plugin Bundles
        val barcodeBundle = BarcodePlugin.Builder()
            .setEnabled(true)
            .setEnableBarcodeHighlight()
            .addNewBarcodeHighlightOverlayRule(rule1)
            .addNewBarcodeHighlightOverlayRule(rule2)
            .addNewBarcodeHighlightOverlayRule(rule3)
            .addNewBarcodeHighlightReportDataRule(reportRule)
            .create()

        // Create Main Bundle
        val mainBundle = MainBundle.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPluginBundle(barcodeBundle)
            .create()

        sendDataWedgeUpdatedProfile(mainBundle)
    }

    private fun updateProfileWithWorkflowLicenseDemo() {
        val workflowBundle: Bundle = WorkflowIPlugin.Builder()
            .setWorkflowMode(WorkflowMode.LICENSE_PLATE)
            .setLicenseDecoderModule(
                WorkflowLicenseDecoderModule(
                    WorkflowLicenseDecoderModule.ScanMode.EU,
                    17000,
                    WorkflowOutputImageMode.FULL
                )
            )
            .setCameraModule(WorkflowCameraModule(WorkflowCameraModule.Illumination.OFF))
            .setEnabled(true)
            .create()

        // Create Main Bundle
        val mainBundle = MainBundle.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPluginBundle(workflowBundle)
            .create()

        sendDataWedgeUpdatedProfile(mainBundle)
    }

    private fun updateProfileWithWorkflowIdDemo() {
        val workflowBundle: Bundle = WorkflowIPlugin.Builder()
            .setWorkflowMode(WorkflowMode.ID)
            .setIdentificationDecoderModule(
                WorkflowIdentificationDecoderModule(
                    17000,
                    WorkflowOutputImageMode.NONE
                )
            )
            .setCameraModule(WorkflowCameraModule(WorkflowCameraModule.Illumination.OFF))
            .setEnabled(true)
            .create()

        // Create Main Bundle
        val mainBundle = MainBundle.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPluginBundle(workflowBundle)
            .create()

        sendDataWedgeUpdatedProfile(mainBundle)
    }

    private fun updateProfileWithWorkflowVinDemo() {
        val workflowBundle: Bundle = WorkflowIPlugin.Builder()
            .setWorkflowMode(WorkflowMode.VIN)
            .setVINDecoderModule(
                WorkflowVINDecoderModule(
                    17000,
                    WorkflowOutputImageMode.FULL
                )
            )
            .setCameraModule(WorkflowCameraModule(WorkflowCameraModule.Illumination.OFF))
            .setEnabled(true)
            .create()

        // Create Main Bundle
        val mainBundle = MainBundle.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPluginBundle(workflowBundle)
            .create()

        sendDataWedgeUpdatedProfile(mainBundle)
    }

    private fun updateProfileWithWorkflowTinDemo() {
        val workflowBundle: Bundle = WorkflowIPlugin.Builder()
            .setWorkflowMode(WorkflowMode.TIN)
            .setTINDecoderModule(
                WorkflowTINDecoderModule(
                    17000,
                    WorkflowOutputImageMode.FULL
                )
            )
            .setCameraModule(WorkflowCameraModule(WorkflowCameraModule.Illumination.OFF))
            .setEnabled(true)
            .create()

        // Create Main Bundle
        val mainBundle = MainBundle.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPluginBundle(workflowBundle)
            .create()

        sendDataWedgeUpdatedProfile(mainBundle)
    }

    private fun updateProfileWithWorkflowAnalogMeterDemo() {
        val workflowBundle: Bundle = WorkflowIPlugin.Builder()
            .setWorkflowMode(WorkflowMode.METER)
            .setMeterDecoderModule(
                WorkflowMeterDecoderModule(
                    WorkflowMeterDecoderModule.ScanMode.AUTO_ANALOG_DIGITAL_METER,
                    30000,
                    WorkflowOutputImageMode.FULL
                )
            )
            .setCameraModule(WorkflowCameraModule(WorkflowCameraModule.Illumination.OFF))
            .setEnabled(true)
            .create()

        // Create Main Bundle
        val mainBundle = MainBundle.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPluginBundle(workflowBundle)
            .create()

        sendDataWedgeUpdatedProfile(mainBundle)
    }

    private fun updateProfileWithWorkflowDialMeterDemo() {
        val workflowBundle: Bundle = WorkflowIPlugin.Builder()
            .setWorkflowMode(WorkflowMode.METER)
            .setMeterDecoderModule(
                WorkflowMeterDecoderModule(
                    WorkflowMeterDecoderModule.ScanMode.DIAL_METER,
                    30000,
                    WorkflowOutputImageMode.FULL
                )
            )
            .setCameraModule(WorkflowCameraModule(WorkflowCameraModule.Illumination.OFF))
            .setEnabled(true)
            .create()

        // Create Main Bundle
        val mainBundle = MainBundle.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPluginBundle(workflowBundle)
            .create()

        sendDataWedgeUpdatedProfile(mainBundle)
    }

    private fun updateProfileWithWorkflowContainerDemo() {
        val workflowBundle: Bundle = WorkflowIPlugin.Builder()
            .setWorkflowMode(WorkflowMode.CONTAINER)
            .setContainerDecoderModule(
                WorkflowContainerDecoderModule(
                    30000,
                    WorkflowOutputImageMode.FULL
                )
            )
            .setCameraModule(WorkflowCameraModule(WorkflowCameraModule.Illumination.OFF, 2))
            .setEnabled(true)
            .create()

        // Create Main Bundle
        val mainBundle = MainBundle.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPluginBundle(workflowBundle)
            .create()

        sendDataWedgeUpdatedProfile(mainBundle)
    }

    private fun updateProfileWithWorkflowFreeFormCaptureDemo() {
        val workflowBundle: Bundle = WorkflowIPlugin.Builder()
            .setWorkflowMode(WorkflowMode.FREE_FORM_CAPTURE)
            .setFreeFormCaptureModule(
                WorkflowFreeFormCaptureDecoderModule(
                    WorkflowFreeFormCaptureDecoderModule.Mode.DECODE_AND_HIGHLIGHT,
                    17000
                )
            )
            .setWorkflowInputMode(WorkflowInputMode.CAMERA)
            .setCameraModule(WorkflowCameraModule(WorkflowCameraModule.Illumination.OFF))
            .setEnabled(true)
            .create()

        // Create Main Bundle
        val mainBundle = MainBundle.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPluginBundle(workflowBundle)
            .create()

        sendDataWedgeUpdatedProfile(mainBundle)
    }

    private fun sendDataWedgeUpdatedProfile(mainBundle: Bundle) {
        // Send Intent With Result
        DataWedgeWrapper.sendIntentWithLastResult(
            this, Constants.IntentType.SET_CONFIG,
            mainBundle, "CREATE_PROFILE"
        ) { wasSuccessful, resultInfo, resultString, command, profileName ->
            Log.i(TAG, "Last Result Success: $wasSuccessful")
            Toast.makeText(
                this@TestActivity,
                "Last Result Success: $wasSuccessful",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        // Debugging
        private const val TAG = "TestActivity"
    }
}
package com.zebra.nilac.dwconfigurator

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zebra.nilac.dwconfigurator.configuration.ProfileConfigurator
import com.zebra.nilac.dwconfigurator.configuration.params.barcode.HighlightingParams
import com.zebra.nilac.dwconfigurator.configuration.plugins.BarcodePlugin
import com.zebra.nilac.dwconfigurator.configuration.plugins.WorkflowIPlugin
import com.zebra.nilac.dwconfigurator.databinding.TestActivityBinding
import com.zebra.nilac.dwconfigurator.models.CommandIdentifier
import com.zebra.nilac.dwconfigurator.models.barcode.BarcodeSymbology
import com.zebra.nilac.dwconfigurator.models.barcode.highlight.BarcodeHighlightGenericRule
import com.zebra.nilac.dwconfigurator.models.barcode.highlight.BarcodeHighlightRuleAction
import com.zebra.nilac.dwconfigurator.models.barcode.highlight.BarcodeHighlightRuleCriteria
import com.zebra.nilac.dwconfigurator.models.workflow.WorkflowInputMode
import com.zebra.nilac.dwconfigurator.models.workflow.WorkflowMode
import com.zebra.nilac.dwconfigurator.models.workflow.WorkflowOutputImageMode
import com.zebra.nilac.dwconfigurator.models.workflow.modules.WorkflowCameraModule
import com.zebra.nilac.dwconfigurator.models.workflow.modules.WorkflowContainerDecoderModule
import com.zebra.nilac.dwconfigurator.models.workflow.modules.WorkflowFreeFormCaptureDecoderModule
import com.zebra.nilac.dwconfigurator.models.workflow.modules.WorkflowIdentificationDecoderModule
import com.zebra.nilac.dwconfigurator.models.workflow.modules.WorkflowLicenseDecoderModule
import com.zebra.nilac.dwconfigurator.models.workflow.modules.WorkflowMeterDecoderModule
import com.zebra.nilac.dwconfigurator.models.workflow.modules.WorkflowTINDecoderModule
import com.zebra.nilac.dwconfigurator.models.workflow.modules.WorkflowVINDecoderModule

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


        val symbologies = ArrayList<BarcodeSymbology>().apply {
            add(BarcodeSymbology.QRCODE)
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

        val barcodeHighlightingParams = HighlightingParams.Builder()
            .addNewBarcodeHighlightOverlayRule(rule1)
            .addNewBarcodeHighlightOverlayRule(rule2)
            .addNewBarcodeHighlightOverlayRule(rule3)
            .addNewBarcodeHighlightReportDataRule(reportRule)
            .setEnabled(true)
            .create()

        // Create Plugin Bundles
        val barcodeBundle = BarcodePlugin.Builder()
            .addBarcodeHighlightingParams(barcodeHighlightingParams)
            .setEnabled(true)
            .create()

        // Create Main Bundle
        val mainBundle = ProfileConfigurator.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPlugin(barcodeBundle)
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
        val mainBundle = ProfileConfigurator.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPlugin(workflowBundle)
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
        val mainBundle = ProfileConfigurator.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPlugin(workflowBundle)
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
        val mainBundle = ProfileConfigurator.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPlugin(workflowBundle)
            .create()

        sendDataWedgeUpdatedProfile(mainBundle)
    }

    private fun updateProfileWithWorkflowTinDemo() {
        val workflowBundle: Bundle = WorkflowIPlugin.Builder()
            .setWorkflowMode(WorkflowMode.TIN)
            .setTINDecoderModule(
                WorkflowTINDecoderModule(
                    WorkflowTINDecoderModule.ScanMode.UNIVERSAL,
                    17000,
                    WorkflowOutputImageMode.FULL
                )
            )
            .setCameraModule(WorkflowCameraModule(WorkflowCameraModule.Illumination.OFF))
            .setEnabled(true)
            .create()

        // Create Main Bundle
        val mainBundle = ProfileConfigurator.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPlugin(workflowBundle)
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
        val mainBundle = ProfileConfigurator.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPlugin(workflowBundle)
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
        val mainBundle = ProfileConfigurator.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPlugin(workflowBundle)
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
        val mainBundle = ProfileConfigurator.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPlugin(workflowBundle)
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
        val mainBundle = ProfileConfigurator.Builder()
            .setProfileName("DWDemo")
            .setProfileEnabled(true)
            .addPlugin(workflowBundle)
            .create()

        sendDataWedgeUpdatedProfile(mainBundle)
    }

    private fun sendDataWedgeUpdatedProfile(mainBundle: Bundle) {
        // Send Intent With Result
        DataWedgeWrapper.sendIntentWithLastResult(
            this, Constants.IntentType.SET_CONFIG,
            mainBundle, CommandIdentifier.CREATE_PROFILE
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
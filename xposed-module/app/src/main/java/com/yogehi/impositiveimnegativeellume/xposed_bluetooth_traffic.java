package com.yogehi.impositiveimnegativeellume;

import java.util.Arrays;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.getObjectField;
import static de.robv.android.xposed.XposedHelpers.getStaticObjectField;

public class xposed_bluetooth_traffic implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        /** checks for Ellume Home COVID Test **/
        if (!lpparam.packageName.equals("com.ellumehealth.homecovid.android"))
            return;
        XposedBridge.log("[#yay#] Hooked into Ellume Home COVID Test App");

        String[] yayresulttextyay = {"NO_RESULT", "NEGATIVE", "POSITIVE_T2", "POSITIVE_T1", "POSITIVE_T1_T2", "T1_CONJUGATE_WAVE_TOO_EARLY", "T2_CONJUGATE_WAVE_TOO_EARLY", "DARK_FREQUENCY_TOO_HIGH", "ZERO_C1_C2", "FREQUENCY_ZERO_TEST_LINE_1", "FREQUENCY_ZERO_TEST_LINE_2", "NO_CONJUGATE_WAVE_DETECTED", "DRY_FREQUENCY_OUT_OF_RANGE_TEST_LINE_1", "PEAK_FREQUENCY_TOO_LOW_TEST_LINE_1", "FINAL_FREQUENCIES_TOO_HIGH", "DRY_FREQUENCY_OUT_OF_RANGE_TEST_LINE_2", "PEAK_FREQUENCY_TOO_LOW_TEST_LINE_2", "CONTROL_2", "T1_T2_PEAK_DELTA_TOO_LONG", "RESULT_TIMEOUT", "T1_NEGATIVE_WAVE", "T2_NEGATIVE_WAVE", "SIGNAL_NOISE_HIGH", "C1_DRY_OUT_OF_RANGE", "C1_PEAK_LOW", "C2_DRY_OUT_OF_RANGE", "C2_PEAK_LOW", "NEGATIVE_WAVE_C1", "NEGATIVE_WAVE_C2", "CONTROL_WAVE_EARLY"};
        String[] yayalgorithmstatetextyay = {"NOT_STARTED", "AWAITING_CONJUGATE_WAVE", "AWAITING_PEAK_TEST_LINE_1", "AWAITING_PEAK_TEST_LINE_2", "MEASURING_AT_ONE_SECOND_INTERVAL", "MEASURING_AT_FIVE_SECOND_INTERVAL_NORMAL", "MEASURING_AT_FIVE_SECOND_INTERVAL_EXTENDED", "RESULT_AVAILABLE", "PREVIOUS_RESULT"};
        String[] yayhardwarestatusstringyay = {"OK", "UNLOADED_VOLTAGE_LOW_3V3", "UNLOADED_VOLTAGE_LOW_5V0", "LOADED_VOLTAGE_LOW_5V0", "LED_CURRENT_LOW_TEST_LINE_1", "LED_CURRENT_HIGH_TEST_LINE_1", "LED_CURRENT_LOW_TEST_LINE_2", "LED_CURRENT_HIGH_TEST_LINE2", "DARK_FREQUENCY_HIGH"};
        String[] yaystatustextyay = {"PERFORMING_SELF_TEST", "AWAITING_START", "USED", "SELF_TEST_FAILED", "PROCESSING", "RESULT", "STOPPED", "UNKNOWN"};

        Class<?> BluetoothGattClass = XposedHelpers.findClass("android.bluetooth.BluetoothGattCharacteristic", lpparam.classLoader);

        findAndHookMethod(BluetoothGattClass, "getValue", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                byte[] yaybyteyay = (byte[]) param.getResult();

                if (yaybyteyay[2] == 12){ // MEASUREMENT_CONTROL_DATA
                    int yaycalculateyay = 0;
                    int yayuniqueidintyay = util_ellume_functions.getUInt32(Arrays.copyOfRange(yaybyteyay, 4, 8));
                    short yaysequencenumbershortyay = util_ellume_functions.toShort(Arrays.copyOfRange(yaybyteyay, 8, 10));
                    short yaytimestamptshortyay = util_ellume_functions.toShort(Arrays.copyOfRange(yaybyteyay, 10, 12));
                    int yayline1intyay = util_ellume_functions.toInt24(Arrays.copyOfRange(yaybyteyay, 12, 15));
                    byte yayalgorithmstateyay = yaybyteyay[15];
                    int yayline2intyay = util_ellume_functions.toInt24(Arrays.copyOfRange(yaybyteyay, 16, 19));
                    byte yayhardwarestatusyay = yaybyteyay[19];
                    short yaydarkfrequencyshortyay = util_ellume_functions.toShort(Arrays.copyOfRange(yaybyteyay, 20, 22));
                    byte yayresultyay = yaybyteyay[22];
                    short yayc1valueyay = util_ellume_functions.toShort(Arrays.copyOfRange(yaybyteyay, 23, 25));
                    short yayc2valueyay = util_ellume_functions.toShort(Arrays.copyOfRange(yaybyteyay, 25, 27));
                    byte yaygivenchecksumyay = yaybyteyay[27];
                    XposedBridge.log(
                            "[#yay#] MEASUREMENT_CONTROL_DATA:" +
                                    "\n\t[#yay#] byte array: " + yaybyteyay +
                                    "\n\t[#yay#] test result: " + yayresultyay + " " + yayresulttextyay[yayresultyay] +
                                    "\n\t[#yay#] algorithm state: " + yayalgorithmstateyay + " " + yayalgorithmstatetextyay[yayalgorithmstateyay] +
                                    "\n\t[#yay#] timestamp: " + yaytimestamptshortyay +
                                    "\n\t[#yay#] unique id: " + yayuniqueidintyay +
                                    "\n\t[#yay#] line 1: " + yayline1intyay +
                                    "\n\t[#yay#] line 2: " + yayline2intyay +
                                    "\n\t[#yay#] c1 value: " + yayc1valueyay +
                                    "\n\t[#yay#] c2 value: " + yayc2valueyay +
                                    "\n\t[#yay#] hardware status: " + yayhardwarestatusstringyay[yayhardwarestatusyay] +
                                    "\n\t[#yay#] sequence number: " + yaysequencenumbershortyay +
                                    "\n\t[#yay#] darkfrequency: " + yaydarkfrequencyshortyay +
                                    "\n\t[#yay#] given checksum: " + yaygivenchecksumyay +
                                    "\n\t[#yay#] crc value: " + yaybyteyay[28] + " " + yaybyteyay[29]
                    );

                    // DEBUG
                    Boolean debug = false;
                    if (debug == true) {
                        yaybyteyay[22] = 1;
                        XposedBridge.log("\t[-yay-] DEBUG - set result to NEGATIVE");
                        yaybyteyay[15] = 7;
                        XposedBridge.log("\t[-yay-] DEBUG - set algorithm to RESULT_AVAILABLE");
                    }

                    //if test is negative then change to positive
                    if (yaybyteyay[22] == 1){
                        XposedBridge.log("\t[-yay-] Test was NEGATIVE, changing to POSITIVE");
                        yaybyteyay[22] = 2;
                        yaycalculateyay = 1;
                        //if test is positive then change to negative
                    } else if (yaybyteyay[22] == 2){
                        XposedBridge.log("\t[-yay-] Test was POSITIVE, changing to NEGATIVE");
                        yaybyteyay[22] = 1;
                        yaycalculateyay = 1;
                    }

                    if (yaycalculateyay == 1) {
                        // calculate checksum of smol area of byte array
                        // 1) calculate new checksum value to int
                        int yaysmolyay = util_ellume_functions.b(Arrays.copyOfRange(yaybyteyay, 4, 27));
                        // 2) convert int to byte[] value
                        byte[] yaynewchecksumyay = util_ellume_functions.toBytes(yaysmolyay);
                        // 3) assign new smol checksum, its byte[0]
                        yaybyteyay[27] = yaynewchecksumyay[0];

                        // modify crc value
                        // 1) calculate new CRC short/int value
                        int yaytointyay = util_ellume_functions.b(Arrays.copyOfRange(yaybyteyay, 0, yaybyteyay.length - 2));
                        // 2) convert int to byte array
                        byte[] yaytobytearray = util_ellume_functions.toBytes(yaytointyay);
                        // 3) replace last 2 bytes with new CRC value
                        yaybyteyay[28] = yaytobytearray[0];
                        yaybyteyay[29] = yaytobytearray[1];
                        XposedBridge.log(
                                "\t\t[#yay#] calculated new checksum and CRC value" +
                                        "\n\t\t[#yay#] NEW byte array: " + yaybyteyay
                        );
                        param.setResult(yaybyteyay);

                    }
                } else if (yaybyteyay[2] == 4) { // STATUS
                    int yaycalculateyay = 0;
                    int yayuniqueidintyay = util_ellume_functions.getUInt32(Arrays.copyOfRange(yaybyteyay, 4, 8));
                    byte yaystatusyay = yaybyteyay[8];
                    byte yayalgorithmstateyay = yaybyteyay[9];
                    byte yayresultyay = yaybyteyay[10];
                    short yaymeasurecountyay = util_ellume_functions.toShort(Arrays.copyOfRange(yaybyteyay, 11, 13));
                    short yaytimetoresultyay = util_ellume_functions.toShort(Arrays.copyOfRange(yaybyteyay, 13, 15));
                    short yaytimetofailureyay = util_ellume_functions.toShort(Arrays.copyOfRange(yaybyteyay, 15, 17));

                    XposedBridge.log(
                            "[#yay#] STATUS: " +
                                    "\n\t[#yay#] byte array: " + yaybyteyay +
                                    "\n\t[#yay#] result: " + yayresultyay + " " + yayresulttextyay[yayresultyay] +
                                    "\n\t[#yay#] status: " + yaystatustextyay[yaystatusyay] +
                                    "\n\t[#yay#] algorithm state: " + yayalgorithmstateyay + " " + yayalgorithmstatetextyay[yayalgorithmstateyay] +
                                    "\n\t[#yay#] unique id: " + yayuniqueidintyay +
                                    "\n\t[#yay#] measure count: " + yaymeasurecountyay +
                                    "\n\t[#yay#] time to result: " + yaytimetoresultyay +
                                    "\n\t[#yay#] time to failure: " + yaytimetofailureyay +
                                    "\n\t[#yay#] crc value: " + yaybyteyay[17] + " " + yaybyteyay[18]
                    );

                    // DEBUG
                    Boolean debug = false;
                    if (debug == true){
                        yaybyteyay[10] = 1;
                        XposedBridge.log("\t[-yay-] DEBUG - set result to NEGATIVE");
                        yaybyteyay[8] = 5;
                        XposedBridge.log("\t[-yay-] DEBUG - set status to RESULT");
                        yaybyteyay[9] = 7;
                        XposedBridge.log("\t[-yay-] DEBUG - set algorithm status to RESULT_AVAILABLE");
                    }

                    //if test is negative then change to positive
                    if (yaybyteyay[10] == 1){
                        XposedBridge.log("\t[-yay-] Test was NEGATIVE, changing to POSITIVE");
                        yaybyteyay[10] = 2;
                        yaycalculateyay = 1;
                        //if test is positive then change to negative
                    } else if (yaybyteyay[10] == 2){
                        XposedBridge.log("\t[-yay-] Test was POSITIVE, changing to NEGATIVE");
                        yaybyteyay[10] = 1;
                        yaycalculateyay = 1;
                    }

                    if (yaycalculateyay == 1) {
                        // 1) calculate new CRC short/int value
                        int yaytointyay = util_ellume_functions.b(Arrays.copyOfRange(yaybyteyay, 0, yaybyteyay.length - 2));
                        // 2) convert int to byte array
                        byte[] yaytobytearray = util_ellume_functions.toBytes(yaytointyay);
                        yaybyteyay[17] = yaytobytearray[0];
                        yaybyteyay[18] = yaytobytearray[1];
                        XposedBridge.log(
                                "\t\t[#yay#] calculated new checksum and CRC value" +
                                        "\n\t\t[#yay#] NEW byte array: " + yaybyteyay
                        );
                        param.setResult(yaybyteyay);
                    }
                }
            }
        });
    }
}
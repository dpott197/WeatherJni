#include <stdio.h>
#include "bme280-i2c.h"
#include "si1132.h"
#include "si702x.h"
#include "bmp180.h"

static int pressure;
static int temperature;
static int humidity;
static float altitude;

float SEALEVELPRESSURE_HPA = 1024.25;

int main(int argc, char **argv) {
    int status = 0;
    int WBVersion = 2;
    char *device = "/dev/i2c-1";

    if (argc == 2) {
        device = argv[1];
    } else if (argc > 2) {
        printf("Usage :\n");
        printf("sudo ./weather_board [i2c node](default \"/dev/i2c-1\")\n");
        return -1;
    }

    si1132_begin(device);
    if (bme280_begin(device) < 0) {
        si702x_begin(device);
        bmp180_begin(device);
        WBVersion = 1;
    }

    printf("======== si1132 ========\n");
    printf("UV_index : %.2f\n", Si1132_readUV() / 100.0);
    printf("Visible : %.0f Lux\n", Si1132_readVisible());
    printf("IR : %.0f Lux\n", Si1132_readIR());
    if (WBVersion == 2) {
        bme280_read_pressure_temperature_humidity(
                &pressure, &temperature, &humidity);
        printf("======== bme280 ========\n");
        printf("temperature : %.2lf 'C\n", (double) temperature / 100.0);
        printf("humidity : %.2lf %%\n", (double) humidity / 1024.0);
        printf("pressure : %.2lf hPa\n", (double) pressure / 100.0);
        printf("altitude : %f m\n", bme280_readAltitude(pressure,
                                                        SEALEVELPRESSURE_HPA));
    } else {
        printf("======== bmp180 ========\n");
        printf("temperature : %.2f 'C\n", BMP180_readTemperature());
        printf("pressure : %.2f hPa\n", BMP180_readPressure() / 100);
        printf("Altitude : %.2f meter\n",
               BMP180_readAltitude(SEALEVELPRESSURE_HPA));
        printf("======== si7020 ========\n");
        printf("temperature : %.2f 'C\n", Si702x_readTemperature());
        printf("humidity : %.2f %%\n", Si702x_readHumidity());
    }

    return 0;
}

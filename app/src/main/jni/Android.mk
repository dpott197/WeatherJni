LOG_TO_ANDROID_LOGCAT := true

LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES:= bmp180.c \
                  bme280.c \
                  bme280-i2c.c \
                  si1132.c \
                  si702x.c\
                  weather_board.c

LOCAL_MODULE:=weatherboard

LOCAL_CPPFLAGS+= --sysroot=$(SYSROOT)
LOCAL_CFLAGS+= --sysroot=$(SYSROOT)
LOCAL_CXXFLAGS+= --sysroot=$(SYSROOT)

ifeq ($(LOG_TO_ANDROID_LOGCAT),true)
LOCAL_CFLAGS+= -DDEBUG -DANDROID_NDK
LOCAL_SHARED_LIBRARIES+= libcutils
endif

include $(BUILD_STATIC_LIBRARY)



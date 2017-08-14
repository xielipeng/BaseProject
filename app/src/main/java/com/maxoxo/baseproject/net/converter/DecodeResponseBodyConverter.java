package com.maxoxo.baseproject.net.converter;

import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 。。
 * Created by xielipeng on 2017/1/16.
 */
public class DecodeResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final TypeAdapter<T> adapter;

    DecodeResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
//        String jsonString = GZIPUtils.uncompressToString(value.bytes());
//        Logger.json(jsonString);
        /*try {
            // 解压, 解密
//            jsonString = EncryptionUtil.decryptAES(GZIPUtils.uncompressToString(value.bytes()));
            jsonString = EncryptionUtil.decryptAES();
//            Logger.json(jsonString);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }*/

//        return adapter.fromJson(jsonString);
        return adapter.fromJson("");
    }
}

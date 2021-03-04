package com.example.soc.login;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginResponse
{
        private int code;

        private String data;

        private String success;

        private String message;

        public int getCode ()
        {
            return code;
        }

        public void setCode (int code)
        {
            this.code = code;
        }

        public String getData ()
        {
            return data;
        }

        public void setData (String data)
        {
            this.data = data;
        }

        public String getSuccess ()
        {
            return success;
        }

        public void setSuccess (String success)
        {
            this.success = success;
        }

        public String getMessage ()
        {
            return message;
        }

        public void setMessage (String message)
        {
            this.message = message;
        }
    }

/*
 *   Copyright (C)  2016 android@19code.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package library.artaris.cn.library.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * Create by h4de5ing 2016/5/21 021
 * https://github.com/sharinghuang/ASRabbit/blob/7350ea1c212946633316d36760c7088728dc2730/baselib/src/main/java/com/ht/baselib/utils/FormatVerificationUtils.java
 */
public class VerificationUtils {
    /**
     * 判断姓名格式
     * 真实姓名可以是汉字,也可以是字母,但是不能两者都有,也不能包含任何符号和数字
        1.如果是英文名,可以允许英文名字中出现空格
        2.英文名的空格可以是多个,但是不能连续出现多个
        3.汉字不能出现空格
     * @param value
     * @return
     */
    public static boolean matcherRealName(String value) {
        String regex = "^([\\u4e00-\\u9fa5]+|([a-zA-Z]+\\s?)+)$";
        return testRegex(regex, value);
    }

    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *
     * @param mobile 移动、联通、电信运营商的号码段
     *               <p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *               、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
     *               <p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
     *               <p>电信的号段：133、153、180（未启用）、189</p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean matcherPhoneNum(String mobile) {
        String regex = "(\\+\\d+)?1[3458]\\d{9}$";
        return testRegex(regex, mobile);
    }


    /**
     * 验证固定电话号码
     *
     * @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
     *              <p><b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
     *              数字之后是空格分隔的国家（地区）代码。</p>
     *              <p><b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
     *              对不使用地区或城市代码的国家（地区），则省略该组件。</p>
     *              <p><b>电话号码：</b>这包含从 0 到 9 的一个或多个数字 </p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPhone(String phone) {
        String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
        return testRegex(regex, phone);
    }


    /**
     * 判断账号格式 (4-20位字符)
     * @param value
     * @return
     */
    public static boolean matcherAccount(String value) {
        String regex = "[\\u4e00-\\u9fa5a-zA-Z0-9\\-]{4,20}";
        return testRegex(regex, value);
    }

    /**
     * 判断密码格式 (6-12位字母或数字)
     * @param value
     * @return
     */
    public static boolean matcherPassword(String value) {
        String regex = "^[a-zA-Z0-9]{6,12}$";
        return testRegex(regex, value);
    }

    /**
     * 判断密码格式 (6-12位字母或数字,必须同时包含字母和数字)
     * @param value
     * @return
     */
    public static boolean matcherComplexPassword(String value) {
        String regex = "(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}";
        return testRegex(regex, value);
    }

    /**
     * 判断邮箱格式
     * @param value
     * @return
     */
    public static boolean matcherEmail(String value) {
//        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";      //另一种方式
//        return Pattern.matches(regex, email);
        String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
                "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
                "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
        return testRegex(regex, value);
    }

    /**
     * 判断IP地址
     * @param value
     * @return
     */
    public static boolean matcherIP(String value) {
        String regex = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\" +
                "d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\" +
                "d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
        return testRegex(regex, value.toLowerCase());
    }

    /**
     * 判断URL (http,https,ftp)
     * @param value
     * @return
     */
    public static boolean matcherUrl(String value) {
//        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
//        return Pattern.matches(regex, url);
        String regex = "^(([hH][tT]{2}[pP][sS]?)|([fF][tT][pP]))\\:\\/\\/[\\w-]+\\.\\w{2,4}(\\/.*)?$";
        return testRegex(regex, value.toLowerCase());
    }

    /**
     * 判断中国民用车辆号牌
     * @param value
     * @return
     */
    public static boolean matcherVehicleNumber(String value) {
        String regex = "^[京津晋冀蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼川贵云藏陕甘青宁新渝]?[A-Z][A-HJ-NP-Z0-9学挂港澳练]{5}$";
        return testRegex(regex, value.toLowerCase());
    }

    /**
     * 判断身份证号码格式
     * @param value
     * @return
     */
    public static boolean matcherIdentityCard(String value) {
//        String regex = "^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|" +
//                "(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|" +
//                "\\d{3}[Xx])$)$";
//        return testRegex(regex, value);
        IDCardTester idCardTester = new IDCardTester();
        return idCardTester.test(value);
    }

    private static class IDCardTester {
        public boolean test(String content) {
            if (TextUtils.isEmpty(content)) {
                return false;
            }
            final int length = content.length();
            if (15 == length) {
                try {
                    return isOldCNIDCard(content);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return false;
                }
            } else if (18 == length) {
                return isNewCNIDCard(content);
            } else {
                return false;
            }
        }

        final int[] WEIGHT = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

        final char[] VALID = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

        public boolean isNewCNIDCard(String numbers) {
            numbers = numbers.toUpperCase();
            int sum = 0;
            for (int i = 0; i < WEIGHT.length; i++) {
                final int cell = Character.getNumericValue(numbers.charAt(i));
                sum += WEIGHT[i] * cell;
            }
            int index = sum % 11;
            return VALID[index] == numbers.charAt(17);
        }

        public boolean isOldCNIDCard(String numbers) {
            String yymmdd = numbers.substring(6, 11);
            boolean aPass = numbers.equals(String.valueOf(Long.parseLong(numbers)));
            boolean yPass = true;
            try {
                new SimpleDateFormat("yyMMdd").parse(yymmdd);
            } catch (Exception e) {
                e.printStackTrace();
                yPass = false;
            }
            return aPass && yPass;
        }
    }

    /**
     * 验证整数（正整数和负整数）
     *
     * @param digit 一位或多位0-9之间的整数
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDigit(String digit) {
        String regex = "\\-?[1-9]\\d+";
        return Pattern.matches(regex, digit);
    }

    /**
     * 验证整数和浮点数（正负整数和正负浮点数）
     *
     * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDecimals(String decimals) {
        String regex = "\\-?[1-9]\\d+(\\.\\d+)?";
        return Pattern.matches(regex, decimals);
    }

    /**
     * 验证空白字符
     *
     * @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBlankSpace(String blankSpace) {
        String regex = "\\s+";
        return Pattern.matches(regex, blankSpace);
    }


    /**
     * 是否数值型
     * @param input
     * @return
     */
    public static boolean isNumeric(String input) {
        if (TextUtils.isEmpty(input)) {
            return false;
        }
        char[] chars = input.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        int start = (chars[0] == '-' || chars[0] == '+') ? 1 : 0;
        if (sz > start + 1) {
            if (chars[start] == '0' && chars[start + 1] == 'x') {
                int i = start + 2;
                if (i == sz) {
                    return false;
                }
                for (; i < chars.length; i++) {
                    if ((chars[i] < '0' || chars[i] > '9')
                            && (chars[i] < 'a' || chars[i] > 'f')
                            && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--;
        int i = start;
        while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                if (hasExp) {
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false;
            } else {
                return false;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E') {
                return false;
            }
            if (!allowSigns && (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f' || chars[i] == 'F')) {
                return foundDigit;
            }
            if (chars[i] == 'l' || chars[i] == 'L') {
                return foundDigit && !hasExp;
            }
            return false;
        }
        return !allowSigns && foundDigit;
    }

    /**
     * 验证中文
     *
     * @param chinese 中文字符
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkChinese(String chinese) {
        String regex = "^[\u4E00-\u9FA5]+$";
        return Pattern.matches(regex, chinese);
    }

    /**
     * 验证日期（年月日）
     *
     * @param birthday 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBirthday(String birthday) {
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
        return Pattern.matches(regex, birthday);
    }

    /**
     * 是否匹配正则
     * @param regex
     * @param inputValue
     * @return
     */
    public static boolean testRegex(String regex, String inputValue) {
        return Pattern.compile(regex).matcher(inputValue).matches();
    }

    /**
     * 匹配中国邮政编码
     * @param postcode
     * @return
     */
    public static boolean checkPostcode(String postcode) {
        String regex = "[1-9]\\d{5}";
        return Pattern.matches(regex, postcode);
    }
}

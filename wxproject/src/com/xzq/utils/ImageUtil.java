package com.xzq.utils;

import java.awt.Color;

import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.UnicodeUtil;

public class ImageUtil {
	public static String addText(String oldPath,String newPath,String text) {
		ImgUtil.pressText(//
				FileUtil.file(oldPath), //
				FileUtil.file(newPath), //
				text, Color.black, // 文字
				new Font("黑体", Font.BOLD, 100), // 字体
				10, // x坐标修正值。 默认在中间，偏移量相对于中间偏移
				0, // y坐标修正值。 默认在中间，偏移量相对于中间偏移
				1f// 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
		);
		return "haibao_text.png";
	}

	public static String addImg(String oldPath,String newPath,String path) {
		ImgUtil.pressImage(FileUtil.file(oldPath), 
				FileUtil.file(newPath),
				ImgUtil.read(FileUtil.file(path)), // 水印图片
				-150, // x坐标修正值。 默认在中间，偏移量相对于中间偏移
				-50, // y坐标修正值。 默认在中间，偏移量相对于中间偏移
				1f);
		return newPath;
	}
}

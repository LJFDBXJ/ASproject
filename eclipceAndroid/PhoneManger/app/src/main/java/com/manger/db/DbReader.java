package com.manger.db;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.manger.base.PhoneClass;

	/**
	 * 处理数据库中的数据
	 *
	 * @author LJFDBXJ
	 *
	 */
	public class DbReader {
		public static File telFile;

		static {
			String filepath = "data" + File.separator + "data" + File.separator
					+ "com.manger" + File.separator + "database";

			File dbfile = new File(filepath);

			dbfile.mkdir();

			telFile = new File(dbfile, "commonnum.db");
		}

		/**
		 * 判断文件类型是否存在。
		 */

		public static boolean idExit() {
			if (telFile.exists() && telFile.length() > 0) {
				return true;
			}
			return false;
		}

		/**
		 * 获取本地数据库的数据
		 */

		// 获取commomnum里的数据

		public static List<PhoneClass> getAllDate() { // 获取所有数据的方法

			List<PhoneClass> list = new ArrayList<PhoneClass>();// 写一个实体类的数组

			SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(telFile,
					null);

			String sql = "select*from classlist"; // 查询数据库

			Cursor cursor = database.rawQuery(sql, null); // 获取的数据给游标 第一行

			while (cursor.moveToNext()) {// 检索游标下一条有没有数据，有就放到集合里，没有就跳出循环

				String name = cursor.getString(cursor.getColumnIndex("name"));

				int index = cursor.getInt(cursor.getColumnIndexOrThrow("idx"));
				PhoneClass phoneclass = new PhoneClass(name, index);
				list.add(phoneclass);
			}

			database.close();
			cursor.close();
			return list;

		}

		public static List<PhoneClass> getTwoDate(int idex) { // 获取所有数据的方法

			List<PhoneClass> list = new ArrayList<PhoneClass>();// 写一个实体类的数组

			SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(telFile,
					null);

			String sql = "select*from table" + idex; // 查询数据库

			Cursor cursor = database.rawQuery(sql, null); // 获取的数据给游标 第一行

			while (cursor.moveToNext()) {// 检索游标下一条有没有数据，有就放到集合里，没有就跳出循环

				String name = cursor.getString(cursor.getColumnIndex("name"));
				String number = cursor.getString(cursor
						.getColumnIndexOrThrow("number"));

				PhoneClass phoneclass = new PhoneClass(name, number);
				list.add(phoneclass);
			}

			database.close();
			cursor.close();
			return list;

		}
}

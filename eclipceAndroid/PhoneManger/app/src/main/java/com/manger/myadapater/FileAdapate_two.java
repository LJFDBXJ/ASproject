package com.manger.myadapater;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.manger.R;
import com.manger.base.FileManger_two;
import com.manger.manger.FileManger;
import com.manger.manger.FileTypeManager;
import com.manger.tools.CommonUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FileAdapate_two extends publicAdapte_base<FileManger_two> {
	ListView lv;
	Bitmap icun;
	FileManger filemanger = FileManger.getFilemanger();;

	public FileAdapate_two(Context context, ListView lv) {
		super(context);
		icun = BitmapFactory.decodeResource(context.getResources(),R.drawable.icon_filemgr);
		this.lv = lv;
	}
	/**
	 * 多选
	 */
	public void setAllChexk(boolean b) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFilecheck(b);
		}
		notifyDataSetChanged();// 通知改变
	}

	@Override
	public View myView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;

		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.inflate_filemanger, null);

			viewHolder.Filecheck = (CheckBox) convertView
					.findViewById(R.id.file_checkbox1);
			viewHolder.icun = (ImageView) convertView
					.findViewById(R.id.fileicun_imageView1);
			viewHolder.FileType = (TextView) convertView
					.findViewById(R.id.filename_text_2);
			viewHolder.LastTime = (TextView) convertView
					.findViewById(R.id.filetime_text3);
			viewHolder.FileSize = (TextView) convertView
					.findViewById(R.id.filenumber_text4);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		FileManger_two file = list.get(position);// 得到一个实体对象



		viewHolder.icun.setTag(file.getTargetFile().getAbsoluteFile());
		viewHolder.FileType.setText(file.getFileType() + "");// 文件名字



		long lastTime = file.getTargetFile().lastModified();// 最后修改的时间
		Date date = new Date(lastTime);// 创建一个Date对象
		viewHolder.LastTime.setText(dateFormat.format(date));// 把时间日期格式传入


		long size = file.getTargetFile().length();// 获得文件的长度。
		viewHolder.FileSize.setText(CommonUtil.getFileSize(size));
		viewHolder.Filecheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
										 boolean isChecked) {
				list.get(position).setFilecheck(isChecked);

			}
		});
		viewHolder.Filecheck.setChecked(file.isFilecheck());

		if (FileTypeManager.TYPE_IMAGE.equals(file.getFileType())) {
			Bitmap b = cache.get(file.getTargetFile().getAbsolutePath());
			if (b != null) {
				viewHolder.icun.setImageBitmap(b);

			} else {
				viewHolder.icun.setImageBitmap(icun);
				ansyBitmap(file.getTargetFile().getAbsolutePath());
			}

		} else {
			viewHolder.icun.setImageBitmap(icun);
		}
		return convertView;
	}

	private ExecutorService es = Executors.newFixedThreadPool(10);// 创建一个线程池
	private Handler handler = new Handler();




	LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(1024 * 1024 * 50);
	private void ansyBitmap(final String path) {

		es.execute(new Runnable() {
			@Override
			public void run() {
				final Bitmap btm = cache.get(path);
				if (btm != null) {

					handler.post(new Runnable() {
						public void run() {
							ImageView im = (ImageView) lv.findViewWithTag(path);
							if (im != null && btm != null) {
								im.setImageBitmap(btm);
								return;
							}
						}

					});
				}

				/**
				 * 将创建默认的选项，如果保持不变，
				 * 将从解码器中得到相同的结果，就像NULL通过了一样。
				 */

				BitmapFactory.Options opts = new BitmapFactory.Options();

				/**inJustDecodeBounds设置为true ,解码器将返回空位图，但是仍然设置外字段，
				 * 允许调用方法查询位图，而不必为其像素分配内存
				 */


				opts.inJustDecodeBounds = true;

				/**
				 * 从指定的文件中，文件中获取图片
				 */
				BitmapFactory.decodeFile(path, opts);
				int bl = 1;
				int wbl = 40;
				int hbl = 40;
				if (opts.outWidth > 50) {
					wbl = opts.outWidth / 50;

				}
				if (opts.outHeight > 50) {
					hbl = opts.outHeight / 50;
				}
				bl = wbl > hbl ? wbl : hbl;
				opts.inSampleSize = bl;//inSampleSize 缩放比例。

				opts.inJustDecodeBounds = false;

				final Bitmap bta = BitmapFactory.decodeFile(path, opts);
				// 存
				cache.put(path, bta);
				handler.post(new Runnable() {

					@Override
					public void run() {

						ImageView im = (ImageView) lv.findViewWithTag(path);
						if (im != null) {

							im.setImageBitmap(bta);
						}
					}
				});
			}
		});
	}


	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//时间格式

	public class ViewHolder {
		public CheckBox Filecheck;
		public ImageView icun;
		public TextView FileType;
		public TextView LastTime;
		public TextView FileSize;;
	}
}

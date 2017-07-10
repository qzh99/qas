package com.yc.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;

public class YcAutoCompleteUi {

	protected Shell shell;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		text_1 = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		text_1.setBounds(78, 28, 280, 120);

		//�Զ�����
		text_1.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String temp = text_1.getText();
				//������ı���ĩβ����Ϊ��    ������ǰ���ǿ��ַ�
				int len = temp.length();
				if(len<3){
					return ;
				}
				if((!(temp.charAt(len-1)+"").equals(" "))&&((temp.charAt(len-2)+"").equals(" "))  ||((temp.charAt(len-2)+"").equals("\n"))){
					addNameTextFieldAssist(text_1);				
				}
			}
		});
		addNameTextFieldAssist(text_1);
		
	}
	
	
	
	private void addNameTextFieldAssist(Text text) {
    	TextContentAdapter ad = new TextContentAdapter(){
    		/**
    		 * �ڿؼ�����Ӹùؼ���
    		 * @param control
    		 * @param text
    		 * @param cursorPosition
    		 */
    		@Override
			public void insertControlContents(Control control, String text,int cursorPosition) {
    			Point selection = ((Text) control).getSelection();
		        ((Text) control).insert(text);
		        if (cursorPosition < text.length()) {
		        	((Text) control).setSelection(selection.x + cursorPosition,  selection.x + cursorPosition);
		        }
			}
			/**
			 * ���ùؼ���
			 * @param control
			 * @param text
			 * @param cursorPosition
			 */
			public void setControlContents(Control control, String text,int cursorPosition) {
    			int len = text_1.getText().lastIndexOf(" ");
    			int len1=text_1.getText().lastIndexOf("\n");
    			if(len1>len){
    				len=len1;
    			}
    			String temp = text_1.getText();
    			if(len==-1){
    				super.setControlContents(control, text, cursorPosition);
    			}else{
					temp = temp.substring(0, len+1);
					temp = temp.concat(text);
				super.setControlContents(control, temp, cursorPosition);
				 len=text_1.getText().length();
				 text_1.setSelection(len);
    			}
    			
    		}
			/**
    		 * ���������ؼ���
    		 * @param control
    		 * @return
    		 */
			//@Override
			public String getControlContents(Control control) {
				int len = text_1.getText().lastIndexOf(" ");
    			int len1=text_1.getText().lastIndexOf("\n");
    			if(len1>len){
    				len=len1;
    			}
				String temp = ((Text) control).getText();
				temp = temp.substring(len+1);
				return temp;
			}
			public Point getSelection(Control control){
				Point p= super.getSelection(text_1);
				return p;
			}
			@Override
			public Rectangle getInsertionBounds(Control control) {
			return super.getInsertionBounds(control);
			}
		//	@Override
		public void setSelection(Control control, Point range) {
				super.setSelection(control, range);
		}
    	};
    	
    	
    	
    	
    	
    	AutoCompleteField au = new AutoCompleteField(text,ad , new String[]{});
    	List<String> list = new ArrayList<String>();
    	list.add("select");
    	list.add("drop");
    	list.add("delete");
    	list.add("update");
    	list.add("from");
    	list.add("alter");
    	list.add("create");
    	list.add("table");
    	list.add("where");
    	String strs[] = new String[list.size()];
	    for (int i = 0; i < strs.length; i++) {
			strs[i] = list.get(i).toLowerCase();
		}
	    au.setProposals(strs);
    }
}

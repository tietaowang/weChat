package krelve.demo.rob;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.view.accessibility.AccessibilityNodeInfo;

public class NodeUtils {

	static public boolean getNodeByRootOrText(AccessibilityNodeInfo rootView,
			String contentText) {

		for (int i = 0; i < rootView.getChildCount(); i++) {
			if(rootView.getChild(i) == null){
				continue;
			}
			//System.out.println(rootView.getText() + "");
			if (rootView.getChild(i) != null
					&& rootView.getChild(i).getChildCount() != 0) {
				return getNodeByRootOrText(rootView.getChild(i), contentText);
			}
			if (rootView.getChild(i) != null
					&& rootView.getChild(i).getText() != null
					&& rootView.getChild(i).getText().toString()
							.contains(contentText)) {
				return true;
			}
		}
		//System.out.println(rootView.getText() + "");
		if (rootView != null && rootView.getText() != null
				&& rootView.getText().toString().contains(contentText)) {

			return true;
		}
		return false;
	}

	static public AccessibilityNodeInfo getNode(
			List<AccessibilityNodeInfo> rootlist, String contentText) {
		for (int i = 0; i < rootlist.size(); i++) {
			if (getNodeByRootOrText(rootlist.get(i), contentText)) {
				return rootlist.get(i);
			}
		}
		return null;
	}

	static public AccessibilityNodeInfo getNodeBydiscrip(
			AccessibilityNodeInfo rootlist, String contentText) {
		for (int i = 0; i < rootlist.getChildCount(); i++) {
			if(rootlist.getChild(i) == null){
				continue;
			}
			if (rootlist != null && rootlist.getChild(i) != null && rootlist.getChild(i).getChildCount() > 0) {
				AccessibilityNodeInfo temp =	 getNodeBydiscrip(rootlist.getChild(i), contentText);
				if (temp != null && temp.getContentDescription() != null
						&& contentText.equals(temp
								.getContentDescription().toString())) {
					return temp;
					
				}
			}
			if(rootlist.getChild(i) != null ){
			//	System.out.println(rootlist.getChild(i).getContentDescription()
				//		+ "" + rootlist.getChild(i).getClassName());
			}
			
			if (rootlist.getChild(i) != null && rootlist.getChild(i).getContentDescription() != null
					&& contentText.equals(rootlist.getChild(i)
							.getContentDescription().toString())) {
				return rootlist.getChild(i);
				
			}
		}
		//System.out.println(rootlist.getContentDescription() + ""
			//	+ rootlist.getClassName());
		if (rootlist.getContentDescription() != null
				&& contentText.equals(rootlist.getContentDescription().toString())) {
			
			return rootlist;
			
		}
		return null;
	}

	static public ArrayList<String> regexText(String pattern, String contentText) {

		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(contentText);
		ArrayList<String> array = new ArrayList<String>();
		while (m.find()) {
		//	System.out.println(m.group(1));
			array.add(m.group(1));
		}
		return array;
	}

	static public boolean getNodeByRoot(AccessibilityNodeInfo rootView,
			String contentText) {

		for (int i = 0; i < rootView.getChildCount(); i++) {
			if(rootView.getChild(i) == null){
				continue;
			}
			if (rootView.getChild(i) != null
					&& rootView.getChild(i).getChildCount() > 0) {
				return getNodeByRoot(rootView.getChild(i), contentText);
			}
			//System.out.println(rootView.getText() + "");
			if (rootView.getChild(i) != null
					&& rootView.getChild(i).getText() != null
					&& rootView.getChild(i).getText().toString()
							.contains(contentText)) {
				return true;
			}
		}
	//	System.out.println(rootView.getText() + "");
		if (rootView != null && rootView.getText() != null
				&& rootView.getText().toString().contains(contentText)) {

			return true;
		}
		return false;
	}

	static public String getRandom() {
		int max = 200;
		int min = 10;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return s + "";
	}

	static public AccessibilityNodeInfo getNodeByRootContanisClass(
			AccessibilityNodeInfo rootView, String className) {

		for (int i = 0; i < rootView.getChildCount(); i++) {
			if(rootView.getChild(i) == null){
				continue;
			}
		//	System.out.println(rootView.getChild(i).getText()+""+rootView.getChild(i).describeContents() +""+rootView.getChild(i).getClassName() + "");
			if (rootView.getChild(i).getClassName().toString()
					.equals(className)) {
				return rootView.getChild(i);
			}

			if (rootView.getChild(i).getChildCount() > 0) {
				AccessibilityNodeInfo node = getNodeByRootContanisClass(rootView.getChild(i),className);
				if (node != null && node.getClassName().toString()
						.equals(className)) {
					return node;
				}

			}
			
		}
		//System.out.println(rootView.getText()+""+rootView.describeContents() +""+rootView.getClassName() + "");
		if (rootView != null && rootView.getClassName() != null
				&& rootView.getClassName().toString().contains(className)) {
			return rootView;
		}
		return null;
	}

}

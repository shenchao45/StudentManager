package com.shenchao.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.shenchao.entity.Course;
import com.shenchao.repository.CourseRepository;
import com.shenchao.util.Utils;

public class AddCourseDialog extends JDialog {

	private CallBack callback;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfCourseName;
	private CourseRepository courseRepository = Utils.getBean(CourseRepository.class);

	
	public void setCallback(CallBack callback) {
		this.callback = callback;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddCourseDialog dialog = new AddCourseDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddCourseDialog() {
		setTitle("添加课程");
		setBounds(100, 100, 379, 218);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("课程名称:");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(14, 40, 118, 57);
		contentPanel.add(label);
		
		tfCourseName = new JTextField();
		tfCourseName.setFont(new Font("宋体", Font.PLAIN, 20));
		tfCourseName.setBounds(110, 49, 222, 42);
		contentPanel.add(tfCourseName);
		tfCourseName.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确定");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Course course = new Course();
						course.setCname(tfCourseName.getText());
						courseRepository.save(course);
						Utils.showSuccessDialog(AddCourseDialog.this);
						if (callback!=null) {
							callback.run();
						}
						AddCourseDialog.this.setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AddCourseDialog.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

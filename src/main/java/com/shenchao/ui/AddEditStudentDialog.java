package com.shenchao.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.shenchao.entity.Student;
import com.shenchao.entity.Student.UserType;
import com.shenchao.repository.StudentRepository;
import com.shenchao.util.Utils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddEditStudentDialog extends JDialog {

	private CallBack callBack;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNumber;
	private JTextField tfClass;
	private JTextField tfName;
	private JTextField tfPassword;
	private ButtonGroup group;
	private JRadioButton rb_man;
	private JRadioButton rb_woman;
	public void setCallBack(CallBack callBack) {
		this.callBack = callBack;
	}

	private static StudentRepository studentRepository = Utils.getBean(StudentRepository.class);
	private JRadioButton tbTeacher;
	private JRadioButton rbStudent;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddEditStudentDialog dialog = new AddEditStudentDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddEditStudentDialog() {
		setTitle("添加修改学生");
		setBounds(100, 100, 343, 489);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblBanji = new JLabel("学号:");
			lblBanji.setFont(new Font("宋体", Font.BOLD, 20));
			lblBanji.setBounds(26, 24, 60, 44);
			contentPanel.add(lblBanji);
		}
		{
			tfNumber = new JTextField();
			tfNumber.setFont(new Font("宋体", Font.BOLD, 20));
			tfNumber.setBounds(87, 24, 191, 44);
			contentPanel.add(tfNumber);
			tfNumber.setColumns(10);
		}
		{
			JLabel label = new JLabel("班级:");
			label.setFont(new Font("宋体", Font.BOLD, 20));
			label.setBounds(26, 81, 60, 44);
			contentPanel.add(label);
		}
		{
			tfClass = new JTextField();
			tfClass.setFont(new Font("宋体", Font.BOLD, 20));
			tfClass.setColumns(10);
			tfClass.setBounds(87, 81, 191, 44);
			contentPanel.add(tfClass);
		}
		{
			JLabel label = new JLabel("姓名:");
			label.setFont(new Font("宋体", Font.BOLD, 20));
			label.setBounds(26, 152, 60, 44);
			contentPanel.add(label);
		}
		{
			tfName = new JTextField();
			tfName.setFont(new Font("宋体", Font.BOLD, 20));
			tfName.setColumns(10);
			tfName.setBounds(87, 152, 191, 44);
			contentPanel.add(tfName);
		}
		{
			JLabel label = new JLabel("密码:");
			label.setFont(new Font("宋体", Font.BOLD, 20));
			label.setBounds(26, 221, 60, 44);
			contentPanel.add(label);
		}
		{
			tfPassword = new JTextField();
			tfPassword.setFont(new Font("宋体", Font.BOLD, 20));
			tfPassword.setColumns(10);
			tfPassword.setBounds(87, 221, 191, 44);
			contentPanel.add(tfPassword);
		}
		
		rb_man = new JRadioButton("男");
		rb_man.setSelected(true);
		rb_man.setBounds(108, 359, 60, 27);
		contentPanel.add(rb_man);
		
		rb_woman = new JRadioButton("女");
		rb_woman.setSelected(true);
		rb_woman.setBounds(174, 359, 60, 27);
		contentPanel.add(rb_woman);
		group = new ButtonGroup();
		group.add(rb_man);
		group.add(rb_woman);
		JLabel label = new JLabel("性别:");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(26, 348, 60, 44);
		contentPanel.add(label);
		
		JLabel lblShengf = new JLabel("身份:");
		lblShengf.setFont(new Font("宋体", Font.BOLD, 20));
		lblShengf.setBounds(26, 291, 60, 44);
		contentPanel.add(lblShengf);
		
		rbStudent = new JRadioButton("学生");
		rbStudent.setSelected(true);
		rbStudent.setBounds(108, 302, 60, 27);
		contentPanel.add(rbStudent);
		
		tbTeacher = new JRadioButton("教师");
		tbTeacher.setSelected(true);
		tbTeacher.setBounds(174, 302, 60, 27);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rbStudent);
		buttonGroup.add(tbTeacher);
		contentPanel.add(tbTeacher);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确定");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Student student = new Student();
						student.setSnumber(tfNumber.getText());
						student.setSclass(tfClass.getText());
						student.setSname(tfName.getText());
						student.setSpassword(tfPassword.getText());
						student.setSsex(rb_man.isSelected()?'男':'女');
						student.setUserType(rbStudent.isSelected()?UserType.STUDENT:UserType.TEACHER);
						studentRepository.save(student);
						JOptionPane.showMessageDialog(AddEditStudentDialog.this, "操作成功","提示",JOptionPane.INFORMATION_MESSAGE);
						if (callBack!=null) {
							callBack.run();
						}
						AddEditStudentDialog.this.setVisible(false);
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
						setStudent(null);
						AddEditStudentDialog.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void setStudent(Student student){
		if (student==null) {
			tfClass.setText("");
			tfName.setText("");
			tfNumber.setText("");
			tfPassword.setText("");
			rb_man.setSelected(true);
			rbStudent.setSelected(true);
		}else{
			tfClass.setText(student.getSclass());
			tfName.setText(student.getSname());
			tfPassword.setText(student.getSpassword());
			tfNumber.setText(student.getSnumber());
			rb_man.setSelected(student.getSsex()=='男');
			rb_woman.setSelected(student.getSsex()=='女');
			rbStudent.setSelected(student.getUserType()==UserType.STUDENT);
			tbTeacher.setSelected(student.getUserType()==UserType.TEACHER);
		}
	}
}

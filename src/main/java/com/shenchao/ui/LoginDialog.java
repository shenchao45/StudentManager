package com.shenchao.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.shenchao.entity.Student;
import com.shenchao.repository.StudentRepository;
import com.shenchao.util.Utils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfUserName;
	private JPasswordField pfPasssword;
	private StudentRepository studentRepository = Utils.getBean(StudentRepository.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginDialog dialog = new LoginDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		setTitle("登录");
		setBounds(100, 100, 362, 225);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("用户名:");
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(29, 33, 94, 33);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("密　码:");
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(29, 79, 94, 33);
			contentPanel.add(label);
		}
		{
			tfUserName = new JTextField();
			tfUserName.setBounds(113, 30, 198, 33);
			contentPanel.add(tfUserName);
			tfUserName.setColumns(10);
		}

		pfPasssword = new JPasswordField();
		pfPasssword.setBounds(113, 79, 198, 33);
		contentPanel.add(pfPasssword);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确定");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Student student = studentRepository.findBySnumberAndSpassword(tfUserName.getText(),
								new String(pfPasssword.getPassword()));
						if (student == null) {
							Utils.showErrorDialog(LoginDialog.this, "账号或者密码错误，登录失败");
						} else {
							if (student.getUserType() == com.shenchao.entity.Student.UserType.ADMIN) {
								System.out.println("admin....");
							} else if (student.getUserType() == com.shenchao.entity.Student.UserType.STUDENT) {
								System.out.println("student....");
							} else if (student.getUserType() == com.shenchao.entity.Student.UserType.TEACHER) {
								System.out.println("teacher....");
							}
							Utils.showSuccessDialog(LoginDialog.this,"登录成功，欢迎您:"+student.getSname());
							LoginDialog.this.setVisible(false);
							MainUI.main(null);
						}
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
						LoginDialog.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

package com.myspringmvc.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class AsmUtil {
	
	// 拿到指定的Method的入参名们（返回数组，按照顺序返回）
    public static String[] getMethodParamNames(Method method) throws IOException {
        final String methodName = method.getName();
        final Class<?>[] methodParameterTypes = method.getParameterTypes();
        final int methodParameterCount = methodParameterTypes.length;
        String className = method.getDeclaringClass().getName();
        final boolean isStatic = Modifier.isStatic(method.getModifiers());
        final String[] methodParametersNames = new String[methodParameterCount];


        // 使用org.objectweb.asm.ClassReader来读取到此方法
        System.out.println(className);
        ClassReader cr = new ClassReader(AsmUtil.class.getClassLoader().getResourceAsStream(className.replace('.', '/') + ".class"));
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);


        // 这一步是最红要的，开始visitor浏览了
        // ClassAdapter是org.objectweb.asm.ClassVisitor的子类~~~~
        cr.accept(new ClassAdapter(cw) {

            // 因为此处我们只关心对方法的浏览，因此此处只需要复写此方法即可
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                final Type[] argTypes = Type.getArgumentTypes(desc);

                // 只visitor方法名相同和参数类型都相同的方法~~~
                if (!methodName.equals(name) || !matchTypes(argTypes, methodParameterTypes)) {
                    return mv;
                }

                // 构造一个MethodVisitor返回 重写我们关心的方法visitLocalVariable~~~
                return new MethodAdapter(mv) {

                    //特别注意：如果是静态方法，第一个参数就是方法参数，非静态方法，则第一个参数是 this ,然后才是方法的参数
                    @Override
                    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
                        // 处理静态方法与否~~
                        int methodParameterIndex = isStatic ? index : index - 1;
                        if (0 <= methodParameterIndex && methodParameterIndex < methodParameterCount) {
                            methodParametersNames[methodParameterIndex] = name;
                        }
                        super.visitLocalVariable(name, desc, signature, start, end, index);
                    }
                };
            }
        }, 0);
        return methodParametersNames;
    }
    
    /**
     * 比较参数是否一致
     */
    private static boolean matchTypes(Type[] types, Class<?>[] parameterTypes) {
        if (types.length != parameterTypes.length) {
            return false;
        }
        for (int i = 0; i < types.length; i++) {
            if (!Type.getType(parameterTypes[i]).equals(types[i])) {
                return false;
            }
        }
        return true;
    }
	
}

##  如果内容对你有帮助的话，点一个免费的star吧，非常感谢!

# 1 策略模式

## 1.1 简介

策略模式将业务和数据分离

先统一数据，再处理业务

策略模式的实现需要：

- 一个接口或者抽象类，里面两个方法（一个方法匹配类型，一个可替换的逻辑实现方法）
- 不同策略的差异化实现(就是说，不同策略的实现类)

## 1.2 订单支付演示

### 1.2.1 Pay父类

```java
public interface Pay {
    String getName();
    Double queryBalance(String uid);
    // 默认的支付方法
    default void pay(String uid,Double price){
        Double currentAmount  = queryBalance(uid);
        if(currentAmount < price){
            System.out.println(uid + " 余额不足");
        }else{
            System.out.println(uid + " 支付成功");
        }
    }
}
```

### 1.2.2 支付子类:

#### 1.2.2.1 支付宝类:

```java
public class AliPay implements Pay{
    @Override
    public String getName() {
        return "ALIPAY";
    }

    @Override
    public Double queryBalance(String uid) {
        // 查询余额
        return 90.0;
    }
}
```

#### 1.2.2.2 微信类：

```java
public class WechatPay implements Pay{

    @Override
    public String getName() {
        return "WECHAT_PAY";
    }

    @Override
    public Double queryBalance(String uid) {
        return 110.0;
    }
}
```

#### 1.2.2.3 银行卡类：

```java
public class BankCardPay implements Pay{
    @Override
    public String getName() {
        return "BANKCARD_PAY";
    }

    @Override
    public Double queryBalance(String uid) {
        return 100.0;
    }
}
```

### 1.2.3 支付工厂:

```java
public class PayFactory {
    private static final Map<String,Pay> METHOD = new HashMap<>();
    static {
        METHOD.put(MethodKey.ALIPAY,new AliPay());
        METHOD.put(MethodKey.WECHAT_PAY,new WechatPay());
        METHOD.put(MethodKey.BANKCARD_PAY,new BankCardPay());
    }
    private interface MethodKey {
        String ALIPAY = "ALIPAY";
        String WECHAT_PAY = "WECHAT_PAY";
        String BANKCARD_PAY = "BANKCARD_PAY";
    }
    // 根据不同的key，返回对应的支付方式
    public static Pay getMethod(String key){
        if(METHOD.containsKey(key)){
            return METHOD.get(key);
        }
        return new AliPay();
    }
}
```

### 1.2.4 订单类:

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order implements Serializable {
    private String uid;
    private double money;
    public void pay(String payType){
        Pay pay = PayFactory.getMethod(payType);
        pay.pay(uid,money);
    }
}
```

### 1.2.5 测试类:

```java
public class DesignModeApplication {
    public static void main(String[] args) {
        Order order = new Order("1",100);
        order.pay("ALIPAY");
        order.pay("WECHAT_PAY");
        order.pay("BANKCARD_PAY");
    }
}
```

# 2 责任链模式

## 2.1 简介

当你想要让一个**以上的对象**有机会能够处理某个请求的时候，就使用**责任链模式**。

> 责任链模式为请求创建了一个接收者对象的链。执行链上有多个对象节点，每个对象节点都有机会（条件匹配）处理请求事务，如果某个对象节点处理完了，就可以根据实际业务需求传递给下一个节点继续处理或者返回处理完毕。这种模式给予请求的类型，对请求的发送者和接收者进行解耦。

责任链模式实际上是一种处理请求的模式，它让多个处理器（对象节点）都有机会处理该请求，直到其中某个处理成功为止。责任链模式把多个处理器串成链，然后让请求在链上传递

实现责任链：

- 一个接口或者抽象类
- 每个对象差异化处理
- 对象链（数组）初始化（连起来）

## 2.2 公司处理事务演示

### 2.2.1 User父类：

```java
public abstract class User {
    Integer level;
    private User nextUser;
    public User(Integer level){
        // 每个对象差异化处理
        this.level=level;
    }
    public final void handle(Request request){
        if (Objects.equals(request.getLevel(), level)){
            this.report(request);
        }else {
            this.nextUser.handle(request);
        }
    }
    public void setNextUser(User user){
        this.nextUser=user;
    }
    public abstract void report(Request request);
}
```

### 2.2.2 员工子类:

#### 雇员类:

```java
public class Employee extends User{
    public Employee() {
        super(1);
    }
    @Override
    public void report(Request request) {
        System.out.println("员工处理请求");
    }
}
```

#### 2.2.2.1 组长类:

```java
public class Leader extends User{
    public Leader() {
        super(2);
    }
    @Override
    public void report(Request request) {
        System.out.println("组长处理请求");
    }
}
```

#### 2.2.2.2 老板类:

```java
public class Boss extends User{
    public Boss() {
        super(3);
    }
    @Override
    public void report(Request request) {
        System.out.println("老板处理请求");
    }
}
```

### 2.2.3 请求类：

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Request implements Serializable {
    // 请求等级
    private Integer level;
}
```

### 2.2.4 测试类:

```java
public class Application {
    public static void main(String[] args) {
        Request request1 = new Request(1);
        Request request2 = new Request(2);
        Request request3 = new Request(3);
        Boss boss = new Boss();
        Employee employee = new Employee();
        Leader leader = new Leader();
        employee.setNextUser(leader);
        leader.setNextUser(boss);

        employee.handle(request1);
        System.out.println("=====");
        employee.handle(request2);
        System.out.println("=====");
        employee.handle(request3);
    }
}
```

# 3 模板方法模式

## 3.1 简介

定义一个操作中的算法的骨架流程，而将一些步骤延迟到子类中，使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。它的核心思想就是：定义一个操作的一系列步骤，对于某些暂时确定不下来的步骤，就留给子类去实现，这样不同的子类就可以定义出不同的步骤。

> 模式举例：追女朋友要先“牵手”，再“拥抱”，再“接吻”， 再“拍拍..额..手”。至于具体你用左手还是右手牵，无所谓，但是整个过程，定了一个流程模板，按照模板来就行。

## 3.2 共享单车骑行演示

### 3.2.1 Bicycle父类

```java
public abstract class Bicycle {
    public void unlock(){
        System.out.println("密码开锁");
    }
    public void ride(){
        System.out.println("开始骑行");
    }
    public void use(){
        unlock();
        ride();
    }
}
```

### 3.2.2 自行车子类:

#### 3.2.2.1 子类1

```java
public class BicycleType1 extends Bicycle{
    @Override
    public void unlock() {
        System.out.println("扫码开锁");
    }
}
```

#### 3.2.2.2 子类2

```java
public class BicycleType2 extends Bicycle{
}
```

### 3.2.3 测试类

```java
public class TemplateApplication {
    public static void main(String[] args) {
        BicycleType1 bicycleType1 = new BicycleType1();
        BicycleType2 bicycleType2 = new BicycleType2();
        bicycleType1.use();
        System.out.println("=============");
        bicycleType2.use();
    }
}
```

# 4 观察者模式

## 4.1 简介

> 观察者模式定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被完成业务的更新。

观察者模式属于行为模式，一个对象（被观察者）的状态发生改变，所有的依赖对象（观察者对象）都将得到通知，进行广播通知。它的主要成员就是**观察者和被观察者**。

- 被观察者（Observerable）：目标对象，状态发生变化时，将通知所有的观察者。
- 观察者（observer）：接受被观察者的状态变化通知，执行预先定义的业务。

**使用场景：** 完成某件事情后，异步通知场景。如，登陆成功，发个IM消息等等。

## 4.2 发送消息演示：

### 4.2.1 被观察者：

```java
public class Observed {
    private HashSet<Observer> observers=new HashSet<>();
    private int state;
    public Observed(int state){
        this.state=state;
    }
    public void addObserver(Observer observer){
        observers.add(observer);
    }
    public void handle(int state){
        if (state!=this.state){
            System.out.println("还不需要通知");
        }else {
            observers.forEach(Observer::doThing);
        }
    }
}
```

### 4.2.2 观察者父类

```java
public interface Observer {
    void doThing();
}
```

### 4.2.3 观察者子类

#### 4.2.3.1 发送邮件

```java
public class ObserverEmail implements Observer{
    @Override
    public void doThing() {
        System.out.println("发送邮件");
    }
}
```

#### 4.2.3.2 发送短信

```java
public class ObserverPhone implements Observer{
    @Override
    public void doThing() {
        System.out.println("发送短信");
    }
}
```

#### 4.2.3.3 发送IM

```java
public class ObserverIM implements Observer{
    @Override
    public void doThing() {
        System.out.println("发送IM");
    }
}
```

### 4.2.4 测试类

```java
public class ObserverApplication {
    public static void main(String[] args) {
        Observed observed = new Observed(1);
        observed.addObserver(new ObserverEmail());
        observed.addObserver(new ObserverIM());
        observed.addObserver(new ObserverPhone());
        observed.handle(1);
    }
}
```

# 5 工厂模式

## 5.1 简介

定义一个创建对象的接口，让其子类自己决定实例化哪一个工厂类，工厂模式使其创建过程延迟到子类进行。

## 5.2 代码演示

```java
public class FactoryUser {
    public User getUser(Integer type){
        switch (type){
            case 1:
                return new Student();
            case 2:
                return new Teacher();
            default:
                return new DefaultUser();
        }
    }
}
```

# 6 单例模式

## 6.1 简介

**保证一个类仅有一个实例**，并提供一个访问它的全局访问点

## 6.2 单例模式的不同写法

### 6.2.1 饿汉模式

```java
public class Hungry {
    private static Lock lock=new Lock();
    public static Lock getLock(){
        return lock;
    }
}
```

### 6.2.2 懒汉模式

```java
public class Lazy {
    private static Lock lock;
    public static Lock getLock(){
        if (lock==null){
            lock=new Lock();
            return lock;
        }else {
            return lock;
        }
    }
}
```

### 6.2.3 双重校验锁模式

```java
public class CheckLock {
    private static Lock lock;
    public static Lock getLock(){
        if (lock==null){
            synchronized (CheckLock.class) {
                if (lock == null) {
                    lock=new Lock();
                    return lock;
                }
            }
        }
        return lock;
    }
}
```

### 6.2.4 静态内部类

```java
public class Inside {
    public static class InsideIn{
        public static final Lock LOCK=new Lock();
    }
    public static Lock getLock(){
        return InsideIn.LOCK;
    }
}
```

### 6.2.5 枚举

```java
public enum Enum {
    LOCK;
    public void useLock(){
      System.out.println("use lock");
    }
}
```

# 7 演示代码下载

阿里云OSS: https://hhuhahaha.oss-cn-hangzhou.aliyuncs.com/code/designModeDemo1.zip

Gitee: https://gitee.com/jk_2_yu/designModeDemo1

Github: https://github.com/llllike/designModeDemo1
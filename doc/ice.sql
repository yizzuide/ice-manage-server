-- 创建数据库
CREATE DATABASE IF NOT EXISTS ice default charset utf8mb4 COLLATE utf8mb4_general_ci;

-- 创建相关表
create table sys_department
(
    id bigint auto_increment comment '部门编号',
    department_name varchar(50) not null comment '部门名称',
    phone varchar(50) null comment '部门电话',
    address varchar(255) null comment '部门地址',
    pid bigint not null comment '所属部门编号',
    parent_name varchar(50) not null comment '所属部门名称',
    create_time datetime default now() null comment '创建时间',
    update_time datetime default now() null comment '更新时间',
    order_num int null comment '排序',
    is_delete tinyint default 0 null comment '是否删除（0-未删除 1-删除）',
    constraint sys_department_pk
        primary key (id)
) comment '部门';

insert  into `sys_department`(`id`,`department_name`,`phone`,`address`,`pid`,`parent_name`,`order_num`,`is_delete`) values
(1,'北京兆日网络技术有限公司','020-00000001','北京望京区',0,'',0,0),
(2,'软件技术部','020-00000002','北京望京区',1,'北京兆日网络技术有限公司',1,0),
(3,'人事管理部','020-00000003','北京望京区',1,'北京兆日网络技术有限公司',2,0),
(4,'市场管理部','020-00000004','北京望京区',1,'北京兆日网络技术有限公司',3,0),
(5,'软件研发部','020-00000005','北京望京区',1,'北京兆日网络技术有限公司',4,0);

create table sys_user
(
    id bigint auto_increment comment '用户编号',
    username varchar(50) not null comment '用户名称',
    password varchar(100) not null comment '登录密码',
    is_account_expire tinyint default 0 not null comment '用户是否过期（0-未过期，1-过期）',
    is_account_lock tinyint default 0 not null comment '帐号是否锁定（0-未锁定，1-锁定）',
    is_credentials_expire tinyint default 0 not null comment '密码是否过期（0-未过期，1-过期）',
    is_enabled tinyint default 1 not null comment '帐号是否可用（0-不可用，1-可用）',
    real_name varchar(50) not null comment '真实姓名',
    nick_name varchar(50) null comment '昵称',
    department_id bigint null comment '部门编号',
    department_name varchar(50) null comment '部门名称',
    gender tinyint not null comment '性别（0-男，1-女）',
    phone varchar(50) not null comment '手机号',
    email varchar(50) null comment '邮箱',
    avatar varchar(255) null comment '用户图像',
    is_admin tinyint default 0 null comment '是否为超级管理员（0-不是，1-是）',
    create_time datetime default now() null comment '创建时间',
    update_time datetime default now() null comment '更新时间',
    is_delete tinyint default 0 null comment '是否已删除（0-不是，1-是）',
    constraint sys_user_pk
        primary key (id)
) comment '用户表';

# 密码：123456
insert  into `sys_user`(`id`,`username`,`password`,`real_name`,`nick_name`,`department_id`,`department_name`,`gender`,`phone`,`email`,`avatar`,`is_admin`,`is_delete`) values
(1,'admin','$2a$10$kcb35UHVJzhbHO4/vnTs2.rnyxDb9v27/3P/th5hauj2qELERyPka','李小明','超级管理员',1,'北京兆日网络技术有限公司',0,'18567987635','lixiaoming@163.com','',1,0),
(2,'liming','$2a$10$MKwhDmsNvC/IZmZ.Eqfdw.JJTImRUkeXDOuoXHkwEoZrdOjhr/M2G','李小路','小路',2,'软件技术部',0,'17065809726','','',0,0);

create table sys_role
(
    id bigint auto_increment comment '角色编号',
    role_code varchar(50) not null comment '角色编码',
    role_name varchar(50) not null comment '角色名',
    create_user bigint null comment '创建人',
    create_time datetime default now() null comment '创建时间',
    update_time datetime default now() null comment '更新时间',
    remark varchar(255) null comment '角色描述',
    is_delete tinyint default 0 null comment '是否已删除（0-否，1-是）',
    constraint sys_role_pk
        primary key (id)
) comment '角色表';
insert  into `sys_role`(`id`,`role_code`,`role_name`,`create_user`,`remark`,`is_delete`) values
(1,'ROLE_SUPER','超级管理员',1,NULL,0),
(2,'ROLE_DEPART','部门管理员',1,'拥有部门模块的权限',0);

create table sys_user_role
(
    user_id bigint not null comment '用户编号',
    role_id bigint not null comment '角色编号'
) comment '用户角色表';

insert  into `sys_user_role`(`user_id`,`role_id`) values (1,1),(2,2);

create table sys_permission
(
    id bigint auto_increment comment '权限编号',
    label varchar(50) null comment '权限名称',
    parent_id bigint null comment '父权限ID',
    parent_name varchar(50) null comment '父权限名',
    code varchar(50) null comment '授权标识符',
    path varchar(100) null comment '路由地址',
    name varchar(50) null comment '路由名称',
    url varchar(100) null comment '组件路径',
    type tinyint null comment '类型（0-目录，1-菜单，2-按钮）',
    icon varchar(50) null comment '图标',
    create_time datetime default now() null comment '创建时间',
    update_time datetime default now() null comment '更新时间',
    remark varchar(255) null comment '描述',
    order_num int null comment '排序',
    is_delete tinyint default 0 null comment '是否已删除（0-否，1-是）',
    constraint sys_permission_pk
        primary key (id)
) comment '权限表';

insert  into `sys_permission`(`id`,`label`,`parent_id`,`parent_name`,`code`,`path`,`name`,`url`,`type`,`icon`,`remark`,`order_num`,`is_delete`) values
(1,'系统管理',0,'','sys:manager','/system','system',NULL,0,'el-icon-menu',NULL,0,0),
(2,'部门管理',1,'系统管理','sys:department','/system/department','department','/system/department/DepartmentPage',1,'el-icon-s-tools',NULL,0,0),
(3,'查询',2,'部门管理','sys:department:select','','','',2,'el-icon-search',NULL,NULL,0),
(4,'新增',2,'部门管理','sys:department:add',NULL,NULL,NULL,2,'el-icon-plus',NULL,NULL,0),
(5,'修改',2,'部门管理','sys:department:edit',NULL,NULL,NULL,2,'el-icon-edit',NULL,NULL,0),
(6,'删除',2,'部门管理','sys:department:delete',NULL,NULL,NULL,2,'el-icon-delete',NULL,NULL,0),
(7,'用户管理',1,'系统管理','sys:user','/system/userList','userList','/system/user/UserListPage',1,'el-icon-s-custom',NULL,1,0),
(8,'查询',7,'用户管理','sys:user:select','','','',2,'el-icon-search',NULL,NULL,0),
(9,'新增',7,'用户管理','sys:user:add',NULL,NULL,NULL,2,'el-icon-plus',NULL,NULL,0),
(10,'修改',7,'用户管理','sys:user:edit',NULL,NULL,NULL,2,'el-icon-edit',NULL,NULL,0),
(11,'删除',7,'用户管理','sys:user:delete',NULL,NULL,NULL,2,'el-icon-delete',NULL,NULL,0),
(12,'分配角色',7,'用户管理','sys:user:assign','','','',2,'el-icon-setting',NULL,NULL,0),
(13,'角色管理',1,'系统管理','sys:role','/system/roleList','roleList','/system/role/RoleListPage',1,'el-icon-s-tools',NULL,2,0),
(14,'查询',13,'角色管理','sys:role:select','','','',2,'el-icon-search',NULL,NULL,0),
(15,'新增',13,'角色管理','sys:role:add',NULL,NULL,NULL,2,'el-icon-plus',NULL,NULL,0),
(16,'修改',13,'角色管理','sys:role:edit',NULL,NULL,NULL,2,'el-icon-edit',NULL,NULL,0),
(17,'删除',13,'角色管理','sys:role:delete',NULL,NULL,NULL,2,'el-icon-delete',NULL,NULL,0),
(18,'分配权限',13,'角色管理','sys:role:assign','','','',2,'el-icon-setting',NULL,NULL,0),
(19,'菜单管理',1,'系统管理','sys:menu','/system/menuList','menuList','/system/menu/MenuListPage',1,'el-icon-s-tools',NULL,3,0),
(20,'查询',19,'菜单管理','sys:menu:select','','','',2,'el-icon-search',NULL,NULL,0),
(21,'新增',19,'菜单管理','sys:menu:add',NULL,NULL,NULL,2,'el-icon-plus',NULL,NULL,0),
(22,'修改',19,'菜单管理','sys:menu:edit',NULL,NULL,NULL,2,'el-icon-edit',NULL,NULL,0),
(23,'删除',19,'菜单管理','sys:menu:delete',NULL,NULL,NULL,2,'el-icon-delete',NULL,NULL,0),
(24,'任务管理',0,'','service:task','/service','service',NULL,0,'Timer',NULL,4,0),
(25,'Ice延迟任务',24,'任务管理','service:task:ice:select','/service/iceList','iceList','/service/ice/IceListPage',1,'AlarmClock',NULL,0,0),
(26,'查看详细',25,'Ice延迟任务','service:task:ice:detail','','','',2,NULL,NULL,NULL,0),
(27,'模拟推送',25,'Ice延迟任务','service:task:ice:push','','','',2,NULL,NULL,NULL,1),
(28,'重推',25,'Ice延迟任务','service:task:ice:rePush','','','',2,NULL,NULL,NULL,2);


create table sys_role_permission
(
    role_id       bigint not null comment '角色ID',
    permission_id bigint not null comment '权限ID'
) comment '角色权限';

insert into `sys_role_permission`(`role_Id`,`permission_Id`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6);


create table job_inspection
(
    id bigint(32) not null,
    topic varchar(32) not null,
    application_name varchar(20) not null,
    queue_type tinyint(1) not null comment '0: DelayQueue, 1: ReadyQueue, 2: NoneQueue, 3: DeadQueue 4: JobPool',
    bucket_index tinyint(1) default -1 null,
    had_retry_count tinyint(1) default 0 null,
    need_re_push tinyint(1) default 0 null comment '0: No, 1: Yes',
    execution_time datetime default null null comment 'time of next execution',
    push_time datetime default now() null,
    update_time datetime default now() null,
    constraint job_inspection_pk
        primary key (id)
) comment 'job状态描述表';

create index job_inspection_push_time_index
    on job_inspection (push_time);

create index job_inspection_update_time_index
    on job_inspection (update_time desc);
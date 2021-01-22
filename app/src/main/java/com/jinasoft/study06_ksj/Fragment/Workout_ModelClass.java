package com.jinasoft.study06_ksj.Fragment;

class Workout_ModelClass {
    private String name;
    private String description;

    public static final Workout_ModelClass[] workouts = {
            new Workout_ModelClass("림브 루스너","팔굽혀펴기 5회\n 19 1-하체스쿼트 \n15 턱걸이 "),
            new Workout_ModelClass("코어 어고니", "100번 턱걸이, \n100 회 팔굽혀펴기 \n100회 앉았다 일어나기 \n 100회 스쿼트"),
            new Workout_ModelClass("윔프 스페셜", "턱걸이5 \n10회 팔굽혀펴기 \n15회 스쿼트"),
            new Workout_ModelClass("강도 길이 ", "500미터 달리기\n 21 x 1.5 pood 풋캣볼 차기\n21 x 턱걸이")
    };

    public Workout_ModelClass(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

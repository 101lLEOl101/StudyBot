import {
    ActionIcon, Box,
    Button,
    Divider,
    Group,
    Paper,
    PaperProps,
    PasswordInput,
    Stack,
    Text,
    TextInput,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import {Link} from "react-router-dom";
import { IoCloseCircleOutline } from "react-icons/io5";

export function CreateTeacherComponent(props: PaperProps) {
    const form = useForm({
        initialValues: {
            login: '',
            name: '',
            second_name: '',
            password: '',
            repeat_password: '',
        },

        validate: {
            password: (val) => (val.length <= 6 ? 'Пароль должен иметь не менее 6 символов' : null),
            repeat_password: (val, vals) => (val !== vals.password ? 'Повторный пароль неверен' : null)
        },
    });

    return (
        <Paper radius="md" p="xl" pt={"5"} withBorder {...props}>
            <Box display={"flex"} ml={"100%"}>
                <Link to={"/teachers"}>
                    <ActionIcon  radius={100} variant="subtle" color="red">
                        <IoCloseCircleOutline size={32}/>
                    </ActionIcon>
                </Link>
            </Box>
            <Text size="lg" ta={"center"} fw={500}>
                Преподаватель
            </Text>

            <Divider label={'Добавление'} labelPosition="center" my="lg" />

            <form onSubmit={form.onSubmit(() => {})}>
                <Stack>
                    <TextInput
                        label="Логин"
                        placeholder="Логин Преподавателя"
                        value={form.values.login}
                        onChange={(event) => form.setFieldValue('login', event.currentTarget.value)}
                        radius="md"
                    />

                    <TextInput
                        label="Имя"
                        placeholder="Имя Преподавателя"
                        value={form.values.name}
                        onChange={(event) => form.setFieldValue('name', event.currentTarget.value)}
                        radius="md"
                    />

                    <TextInput
                        label="Фамилия"
                        placeholder="Фамилия Преподавателя"
                        value={form.values.second_name}
                        onChange={(event) => form.setFieldValue('second_name', event.currentTarget.value)}
                        radius="md"
                    />

                    <PasswordInput
                        label="Пароль"
                        placeholder="Пароль Преподавателя"
                        value={form.values.password}
                        onChange={(event) => form.setFieldValue('password', event.currentTarget.value)}
                        error={form.errors.password && 'Пароль должен иметь не менее 6 символов'}
                        radius="md"
                    />

                    <PasswordInput
                        label="Повтор Пароля"
                        placeholder="Пароль Преподавателя"
                        value={form.values.repeat_password}
                        onChange={(event) => form.setFieldValue('repeat_password', event.currentTarget.value)}
                        error={form.errors.repeat_password && 'Повторный пароль неверен'}
                        radius="md"
                    />
                </Stack>

                <Group justify="end" mt="xl">
                    <Button type="submit" radius="xl" >
                        Добавить преподавателя
                    </Button>
                </Group>
            </form>
        </Paper>
    );
}